import urllib as ul
import re

# tutorial 1
# ==========

# urls = ["http://gogole.com", "http://cnn.com", "http://nytimes.com"]
# index = 0

# regex = '<title>(.+?)</title>'
# pattern = re.compile(regex)


# while index < len(urls):

# 	htmlfile = ul.urlopen(urls[index])
# 	htmltext =  htmlfile.read()
# 	title = re.findall(pattern, htmltext)
# 	print title
# 	index +=1 





# tutorial 2
# ==========

# symbolslist = ["aapl", "spy", "goog", "nflx"]
# i = 0
# # <span id="yfs_l84_aapl">96.43</span>


# while i < len(symbolslist):

# 	# http://finance.yahoo.com/q?s=aapl
# 	url = "http://finnace.yahoo.com/q?s=" +symbolslist[i] + "&ql=1"
# 	htmlfile = ul.urlopen(url)
# 	htmltext = htmlfile.read()
# 	regex = '<span id="yfs_l84_'+ symbolslist[i] +'"</span>'
# 	pattern = re.compile(regex)
# 	price = re.findall(pattern, htmltext)
# 	print price 
# 	i +=1

import requests
import bs4

root_url = 'http://pyvideo.org'
index_url = root_url + '/category/50/pycon-us-2014'


def get_video_page_urls():

    response = requests.get(index_url)
    soup = bs4.BeautifulSoup(response.text)
    return [a.attrs.get('href') for a in soup.select('div.col-md-3 a[href^=/video]')]

urls =  get_video_page_urls()


def get_video_data(video_page_url):

    video_data = {}
    response = requests.get(root_url + video_page_url)
    soup = bs4.BeautifulSoup(response.text)
    video_data['title'] = soup.select('div#videobox h3')[0].get_text()
    video_data['speakers'] = [a.get_text() for a in soup.select('div#sidebar a[href^=/speaker]')]
    video_data['youtube_url'] = soup.select('div#sidebar a[href^=http://www.youtube.com]')[0].get_text()

    response = requests.get(video_data['youtube_url'])
    soup = bs4.BeautifulSoup(response.text)
    video_data['views'] = int(re.sub('[^0-9]', '',
                                     soup.select('.watch-view-count')[0].get_text().split()[0]))
    # video_data['likes'] = int(re.sub('[^0-9]', '',
    #                                  soup.select('.likes-count')[0].get_text().split()[0]))
    # video_data['dislikes'] = int(re.sub('[^0-9]', '', 
    #                                     soup.select('.dislikes-count')[0].get_text().split()[0]))
    return video_data

count = 0
for url in urls:

	count += 1 
	print count, "\n"
	print get_video_data(url), "\n"

print "\nTHE END"




# page = requests.get("http://www.zillow.com/homedetails/1630-Amalfi-Dr-Pacific-Palisades-CA-90272/20546602_zpid/")
# tree = html.fromstring(page.content)
# price = tree.xpath('//span[@id="yui_3_18_1_1_1464168312477_3548"]/text()')
# print price

















