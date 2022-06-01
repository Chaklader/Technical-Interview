import scrapy
from tutorial.items import DmozItem


class DmozSpider(scrapy.Spider):

    name = "dmoz"
    allowed_domains = ["dmoz.org"]

    start_urls = [
    "http://www.dmoz.org/Computers/Programming/Languages/Python/Books/",
    "http://www.dmoz.org/Computers/Programming/Languages/Python/Resources/"
    ]


    def parse(self, response):

        self.logger.info('A response from %s just arrived!', response.url)
        
        for href in response.css("ul.directory.dir-col > li > a::attr('href')"):
            url = response.urljoin(href.extract())
            yield scrapy.Request(url, callback=self.parse_dir_contents)

        # print "\n\n"

        # for sel in response.xpath('//ul/li'):

        #     item = DmozItem()
        #     item['title'] = sel.xpath('a/text()').extract()
        #     item['link'] = sel.xpath('a/@href').extract()
        #     item['desc'] = sel.xpath('text()').extract()
        #     yield item

        # print "\n\n"

        # filename = response.url.split("/")[-2] + '.html'
        # with open(filename, 'wb') as f:
        #     f.write(response.body)



    def parse_dir_contents(self, response):

        for sel in response.xpath('//ul/li'):
            item = DmozItem()
            item['title'] = sel.xpath('a/text()').extract()
            item['link'] = sel.xpath('a/@href').extract()
            item['desc'] = sel.xpath('text()').extract()
            yield item



    def parse_articles_follow_next_page(self, response):
        
        for article in response.xpath("//article"):
            item = ArticleItem()
            #  some code for the extaction
            yield item

        next_page = response.css("ul.navigation > li.next-page > a::attr('href')")
        
        if next_page:
            url = response.urljoin(next_page[0].extract())
            yield scrapy.Request(url, self.parse_articles_follow_next_page)


##crawling code here 
## -----------------
# scrapy crawl dmoz -o items.json