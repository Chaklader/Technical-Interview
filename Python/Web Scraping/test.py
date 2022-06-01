import scrapy
from scrapy.contrib.spiders import CrawlSpider, Rule
from scrapy.contrib.linkextractors.sgml import SgmlLinkExtractor
from scrapy.contrib.loader.processor import TakeFirst
from scrapy.contrib.loader import XPathItemLoader
# from scrapy.selector import HtmlXPathSelector
from scrapy.selector import Selector
from scrapy.item import Item, Field

import json
import re
import textwrap 
import nltk
from nameparser.parser import HumanName


#  get the human names from the text 
def get_human_names(text):

    tokens = nltk.tokenize.word_tokenize(text)
    pos = nltk.pos_tag(tokens)
    sentt = nltk.ne_chunk(pos, binary = False)
    person_list = []
    person = []
    name = ""
    # for subtree in sentt.subtrees(filter=lambda t: t.node == 'PERSON'):
    for subtree in sentt.subtrees(filter=lambda t: t.label() == 'PERSON'):    
        for leaf in subtree.leaves():
            person.append(leaf[0])
        if len(person) > 1: #avoid grabbing lone surnames
            for part in person:
                name += part + ' '
            if name[:-1] not in person_list:
                person_list.append(name[:-1])
            name = ''
        person = []
    return person_list



class PlayGroundLoader(XPathItemLoader):
    default_output_processor = TakeFirst()

class PlayGroundItem(Item):

    title = Field()
    company_name = Field()
    company_location = Field()
    email = Field()
    phone = Field()
    source = Field()


class GermanyStartupJobs(CrawlSpider):

    name = 'JobsItem'
    start_urls= ['https://www.germanystartupjobs.com/jm-ajax/get_listings/']
        
    def parse(self, response):

        data_list = []

        for idx in xrange(1,18):
            json_data=open("data/" + str(idx) + ".json").read()
            data = json.loads(json_data) 
            data_list.append(data)   

        for item in data_list:

            html = item['html']
            selector = scrapy.Selector(text=item['html'], type="html")
            hrefs = selector.xpath('//a/@href').extract()

            for href in hrefs:
                yield scrapy.Request(href, callback=self.parse_detail)


    def parse_detail(self, response):

        hxs = Selector(response)
        l = PlayGroundLoader(PlayGroundItem(), hxs)

        try:
            full_d  = str(response.xpath\
                ('//div[@class="col-sm-5 justify-text"]//*/text()').extract()) 

            full_des_li = full_d.split(',')
            full_des_lis = []

            for f in full_des_li:
                ff = "".join((f.strip().replace('\n', '')).split())
                if len(ff) < 3:
                    continue 
                full_des_lis.append(f)

            full = 'u'+ str(full_des_lis)

            length = len(full)
            full_des_list = textwrap.wrap(full, length/5)[:-1]

            full_des_list.reverse()


            # get the job title             
            try:
                title = response.css('.job-title').xpath('./text()').extract_first().strip()
            except:
                print "No title"
                title = ''

            # get the company name
            try:
                company_name = response.css('.company-title').xpath('./normal/text()').extract_first().strip()

            except:
                print "No company name"
                company_name = ''


            # get the company location  
            try:
                company_location = response.xpath('//a[@class="google_map_link"]/text()').extract_first().strip()
            except:
                print 'No company location'
                company_location = ''

            # get the job poster email (if available) 
            try:           
                pattern = re.compile(r"(\w(?:[-.+]?\w+)+\@(?:[a-z0-9](?:[-+]?\w+)*\.)+[a-z]{2,})", re.I)
                email = pattern.findall(full_des_list[0])[-1] 
            except:
                email = ''
                pass


            # get the job poster phone number(if available)  
            try:                      
                r = re.compile(".*?(\(?\d{3}\D{0,3}\d{3}\D{0,3}\d{4}).*?", re.S)
                phone = r.findall(full_des_list[0])[-1]

                if len(phone) > 0:
                    phone = '+49-' +phone

            except:
                phone = ''
                pass

                
            # get the name of the poster (if available)
            # try:
            #     for text in full_des_list:
            #         names = get_human_names(text)
            #         if len(names) !=  0:
            #             name = names[-1]
            #             print name
            #             break
            # except:
            #     name = ''
            #     pass
            
            l.add_value('title', title)
            l.add_value('company_name', company_name)
            l.add_value('company_location', company_location)
            l.add_value('email', email)
            l.add_value('phone', phone)
            # l.add_value('name', name)
            l.add_value('source', u"Germany Startup Job")

            return l.load_item()


        except:
            print 'Think better!'
