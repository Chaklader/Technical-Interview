from scrapy.selector import Selector
# from scrapy.http import HtmlResponse
from scrapy.http import HtmlResponse

body = """<html>
			<body>
			<span>good</span>
			<span>bad</span>
			</body>
			</html>
		"""


## 
# extracted = Selector(text=body).xpath('//span/text()').extract()
# for ex in extracted:
# 	print ex



# 
# response = HtmlResponse(url='http://example.com', body=body)
# extracted = Selector(response=response).xpath('//span/text()').extract()
# for ex in extracted:
# 	print ex

# response.selector.xpath('//span/text()').extract()

html = 	"""
		<html>
			<head>
			<base href="http://example.com/" />
			<title>Example website</title>
			</head>
			<body>
				<div id="images">
					<a href="image1.html">Name: My image 1 <br/><img src="image1_thumb.jpg" /></a>
					<a href="image2.html">Name: My image 2 <br/><img src="image2_thumb.jpg" /></a>
					<a href="image3.html">Name: My image 3 <br/><img src="image3_thumb.jpg" /></a>
					<a href="image4.html">Name: My image 4 <br/><img src="image4_thumb.jpg" /></a>
					<a href="image5.html">Name: My image 5 <br/><img src="image5_thumb.jpg" /></a>
				</div>
			</body>
		</html>
		"""

# extracted = Selector(text=html).xpath('//div[@id = "images"]//a/text()').extract()
# for ex in extracted:
# 	print ex


# print  Selector(text=html).xpath('//title/text()').extract()
# print  Selector(text=html).css('title::text').extract()


# sources =  Selector(text=html).css('img').xpath('@src').extract()
# for source in sources:
# 	print source, "n"


# print Selector(text=html).xpath('//div[@id="images"]//a/text()').extract_first()


# print Selector(text=html).xpath('//div[@id="not-exists"]/text()').
# 	  extract_first(default='not-found')


# print Selector(text=html).xpath('//base/@href').extract()
# print Selector(text=html).css('base::attr(href)').extract()


# vals =  Selector(text=html).xpath('//a[contains(@href, "image")]/@href').extract()
# for val in vals:
# 	print val


# vales =  Selector(text=html).css('a[href*=image]::attr(href)').extract()
# for val in vales:
# 	print val




# vls = Selector(text=html).xpath('//a[contains(@href, "image")]/img/@src').extract()
# for vs in vls:
# 	print vs
# print "n"


# vls = Selector(text=html).css('a[href*=image] img::attr(src)').extract()
# for vs in vls:
# 	print vs
# print "n"


# links = Selector(text=html).xpath('//a[contains(@href, "image")]')

# for index, link in enumerate(links):
# 	args = (index, link.xpath('@href').extract(), link.xpath('img/@src').extract())
# 	print 'Link number %d points to url %s and image %s' % args


## regular expression 
# ------------------ 
# names = Selector(text=html).xpath('//a[contains(@href, "image")]/text()').re(r'Name:s*(.*)')
# for name in names:
# 	print name 


# # get the first name  
# first_name = Selector(text=html).xpath('//a[contains(@href, "image")]/text()').re_first(r'Name:s*(.*)')
# print first_name


# divs = Selector(text=html).xpath('//div')
# for p in divs.xpath('.//p'):  # . is for the current div
# 	print p.extract()

# for p in divs.xpath('//p'):  # gets all <p> from the whole document
# 	print p.extract()


##  from the current divs 
# for p in divs.xpath('p'):
# 	print p.extract()


doc = """
		<div>
			<ul>
				<li class="item-0"><a href="link1.html">first item</a></li>
				<li class="item-5"><a href="link2.html">second item</a></li>
				<li class="item-inactive"><a href="link3.html">third item</a></li>
				<li class="item-1"><a href="link4.html">fourth item</a></li>
				<li class="item-0"><a href="link5.html">fifth item</a></li>
			</ul>
		</div>
	"""

sel = Selector(text=doc, type="html")

# print sel.xpath('//li//@href').extract()
# print sel.xpath('//li[re:test(@class, "item-d$")]//@href').extract()








doc = """
		<div itemscope itemtype="http://schema.org/Product">
			<span itemprop="name">Kenmore White 17" Microwave</span>
			<img src="kenmore-microwave-17in.jpg" alt='Kenmore 17" Microwave' />
			<div itemprop="aggregateRating"
			itemscope itemtype="http://schema.org/AggregateRating">
			Rated <span itemprop="ratingValue">3.5</span>/5
			based on <span itemprop="reviewCount">11</span> customer reviews
			</div>

			<div itemprop="offers" itemscope itemtype="http://schema.org/Offer">
			<span itemprop="price">$55.00</span>
			<link itemprop="availability" href="http://schema.org/InStock" />In stock
			</div>

			Product description:
			<span itemprop="description">0.7 cubic feet countertop microwave.
			Has six preset cooking categories and convenience features like
			Add-A-Minute and Child Lock.</span>

			Customer reviews:

			<div itemprop="review" itemscope itemtype="http://schema.org/Review">
			<span itemprop="name">Not a happy camper</span> -
			by <span itemprop="author">Ellie</span>,
			<meta itemprop="datePublished" content="2011-04-01">April 1, 2011
			<div itemprop="reviewRating" itemscope itemtype="http://schema.org/Rating">
			<meta itemprop="worstRating" content = "1">
			<span itemprop="ratingValue">1</span>/
			<span itemprop="bestRating">5</span>stars
			</div>
			<span itemprop="description">The lamp burned out and now I have to replace
			it. </span>
			</div>

			<div itemprop="review" itemscope itemtype="http://schema.org/Review">
			<span itemprop="name">Value purchase</span> -
			by <span itemprop="author">Lucas</span>,
			<meta itemprop="datePublished" content="2011-03-25">March 25, 2011
			<div itemprop="reviewRating" itemscope itemtype="http://schema.org/Rating">
			<meta itemprop="worstRating" content = "1"/>
			<span itemprop="ratingValue">4</span>/
			<span itemprop="bestRating">5</span>stars
			</div>
			<span itemprop="description">Great microwave for the price. It is small and
			fits in my apartment.</span>
			</div>
		</div>
	"""

sel = Selector(text=doc, type="html")

# for scope in sel.xpath('//div[@itemscope]'):
# 	print "current scope:", scope.xpath('@itemtype').extract()
# 	props = scope.xpath('''
# 		set:difference(./descendant::*/@itemprop,
# 		.//*[@itemscope]/*/@itemprop)''')
# 	print "    properties:", props.extract()
# 	print





# 1. //node[1] selects all the nodes occurring first under their respective parents.
# 2. (//node)[1] selects all the nodes in the document, and then gets only the first of them.


sel = Selector(text='<a href="#">Click here to go to the <strong>Next Page</strong></a>')
sel.xpath('//a//text()').extract() # take a peek at the node-set
# [u'Click here to go to the ', u'Next Page']

sel.xpath("string(//a[1]//text())").extract() # convert it to string
# [u'Click here to go to the ']

sel.xpath("//a[1]").extract() # select the first node
# [u'<a href="#">Click here to go to the <strong>Next Page</strong></a>']

sel.xpath("string(//a[1])").extract() # convert it to string
# [u'Click here to go to the Next Page']

sel.xpath("//a[contains(.//text(), 'Next Page')]").extract()
# []	

sel.xpath("//a[contains(., 'Next Page')]").extract()
# [u'<a href="#">Click here to go to the <strong>Next Page</strong></a>']








from scrapy import Selector
sel = Selector(text="""
     <ul class="list">
         <li>1</li>
         <li>2</li>
         <li>3</li>
     </ul>
     <ul class="list">
         <li>4</li>
         <li>5</li>
         <li>6</li>
     </ul>""")

xp = lambda x: sel.xpath(x).extract()


# This gets all first <li> elements under whatever it is its parent:
xp("//li[1]")
# [u'<li>1</li>', u'<li>4</li>']


# And this gets the first <li> element in the whole document:
xp("(//li)[1]")
# [u'<li>1</li>']


# This gets all first <li> elements under an <ul> parent:
xp("//ul/li[1]")
# [u'<li>1</li>', u'<li>4</li>']


# And this gets the first <li> element under an <ul> parent in the whole document:
xp("(//ul/li)[1]")
# [u'<li>1</li>']


from scrapy import Selector
sel = Selector(text='<div class="hero shout"><time datetime="2014-07-23 19:00">Special date</time></div>')
sel.css('.shout').xpath('./time/@datetime').extract()
# [u'2014-07-23 19:00']



