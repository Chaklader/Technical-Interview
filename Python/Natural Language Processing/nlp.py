import nltk
from nameparser.parser import HumanName


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

    return (person_list)


text = """
Some economists have responded positively to Bitcoin, including 
Francois R. Velde, senior economist of the Federal Reserve in Chicago 
who described it as "an elegant solution to the problem of creating a 
digital currency." In November 2013 Richard Branson announced that 
Virgin Galactic would accept Bitcoin as payment, saying that he had invested 
in Bitcoin and found it "fascinating how a whole new global currency 
has been created", encouraging others to also invest in Bitcoin.
Other economists commenting on Bitcoin have been critical. 
Economist Paul Krugman has suggested that the structure of the currency 
incentivizes hoarding and that its value derives from the expectation that 
others will accept it as payment. Economist Larry Summers has expressed 
a "wait and see" attitude when it comes to Bitcoin. Nick Colas, a market 
strategist for ConvergEx Group, has remarked on the effect of increasing 
use of Bitcoin and its restricted supply, noting, "When incremental 
adoption meets relatively fixed supply, it should be no surprise that 
prices go up. And thats exactly what is happening to BTC prices."
"""



names = get_human_names(text)
print "LAST, FIRST"
for name in names: 
    last_first = HumanName(name).last + ', ' + HumanName(name).first
    print last_first


# from nltk.tag.stanford import NERTagger
# st = NERTagger('stanford-ner/all.3class.distsim.crf.ser.gz', 'stanford-ner/stanford-ner.jar')


# for sent in nltk.sent_tokenize(text):
#     tokens = nltk.tokenize.word_tokenize(sent)
#     tags = st.tag(tokens)
#     for tag in tags:
#         if tag[1]=='PERSON': print tag