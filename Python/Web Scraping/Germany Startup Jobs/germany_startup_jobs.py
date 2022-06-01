import scrapy
from scrapy.contrib.spiders import CrawlSpider, Rule
from scrapy.contrib.linkextractors.sgml import SgmlLinkExtractor
from scrapy.contrib.loader.processor import TakeFirst
from scrapy.contrib.loader import XPathItemLoader
from scrapy.selector import HtmlXPathSelector
from scrapy.item import Item, Field

import json
import re
import textwrap 
import nltk
from nameparser.parser import HumanName


# run in the terminal 
# scrapy runspider germany_startup_jobs.py -o germany_startup_jobs.json


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


class PlayGroundItem(Item):

    title = Field()
    company_name = Field()
    company_location = Field()
    email = Field()
    phone = Field()
    source = Field()



class PlayGroundLoader(XPathItemLoader):
    default_output_processor = TakeFirst()


class GermanyStartupJobs(CrawlSpider):

    name = 'JobsItem'
    start_urls= ['https://www.germanystartupjobs.com/jm-ajax/get_listings/']

        
    def parse(self, response):

        data = json.loads(response.body)
        html = data['html']
        selector = scrapy.Selector(text=data['html'], type="html")
        hrefs = selector.xpath('//a/@href').extract()

        for href in hrefs:
            yield scrapy.Request(href, callback=self.parse_detail)


    def parse_detail(self, response):

        hxs = HtmlXPathSelector(response)
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
            full_des_list = textwrap.wrap(full, length/3)[:-1]

            full_des_list.reverse()


            # get the job title             
            try:
                title = response.css('.job-title').xpath('./text()').extract_first().strip()

                # try:
                #     title.decode('unicode_escape').encode('ascii','ignore')
                # except:
                #     pass 
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

                for text in full_des_list:
                    email = pattern.findall(text)[-1]
                    if email is not None:
                        break   
            except:
                print 'No email'
                email = ''

            # get the job poster phone number(if available)                        
            try:
                r = re.compile(".*?(\(?\d{3}\D{0,3}\d{3}\D{0,3}\d{4}).*?", re.S)
                phone = r.findall(full_des_list[0])[-1]

                if phone is not None:
                    phone = '+49-' +phone

            except:
                print 'no phone'
                phone = ''


            # get the name of the poster (if available)
            try:
                for text in full_des_list:
                    names = get_human_names(text)
                    if len(names) !=  0:
                        name = names[-1]
                        print name
                        break
            except:
                print 'no name found'
                name = ''

            l.add_value('title', title)
            l.add_value('company_name', company_name)
            l.add_value('company_location', company_location)
            l.add_value('email', email)
            l.add_value('phone', phone)
            l.add_value('source', u"Germany Startup Job")


            # item = {

            #     'title': title,
            #     'company_name': company_name,
            #     'company_location': company_location, 
            #     # 'poster name': name,
            #     'email': email,
            #     'phone': phone,
            #     'source': u"Germany Startup Job" 
            # }

            # yield item

            return l.load_item()


        except:
            print 'Not valid'
            # raise Exception("Think better!!")




