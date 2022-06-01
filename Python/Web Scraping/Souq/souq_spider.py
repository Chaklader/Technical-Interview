# -*- coding: utf-8 -*-
import scrapy


#  Use <SelectorGadget> Chrome extension to get XPaths or CSS selector. 
#  run the spider and get all the datas using the comamnd:
#  [scrapy runspider souq_spider.py -o deals.csv]

class SouqSpider(scrapy.Spider):

    name = "Souq"  # Name of the Spider, required value
    start_urls = ["http://deals.souq.com/ae-en/"]  # The starting url, Scrapy will request this URL in parse

    # Entry point for the spider
    def parse(self, response):
        for href in response.css('.sp-cms_unit--ttlattr(href)'):
            url = href.extract()
            yield scrapy.Request(url, callback=self.parse_item)

    # Method for parsing a product page
    def parse_item(self, response):
        original_price = -1
        savings=0
        discounted = False
        seller_rating = response.css('.vip-product-infoats .inline-block small::text'). extract()[0]
        seller_rating = int(filter(unicode.isdigit,seller_rating))

        # Not all deals are discounted
        if response.css('.vip-product-infobhead::text').extract():
            original_price = response.css('.vip-product-infobhead::text').extract()[0].replace("AED", "")
            discounted = True
            savings = response.css('.vip-product-infossage .noWrap::text').extract()[0].replace("AED", "")
        yield {
            'Title': response.css('.product-title:text').extract()[0],
            'Category': response.css('.product-title span a+ a::text').extract()[0],
            'OriginalPrice': original_price,
            'CurrentPrice': response.css('.vip-product-infoice::text').extract()[0].replace(u"\xa0", ""),
            'Discounted': discounted,
            'Savings': savings,
            'SoldBy': response.css('.vip-product-infoats a::text').extract()[0],
            'SellerRating': seller_rating,
            'Url': response.url
        }