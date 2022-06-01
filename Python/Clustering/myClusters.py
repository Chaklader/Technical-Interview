print "Implementations of few clustering algorithms "


# k -nearest neighbours algorithms 
# --------------------------------


import csv
import random
import math
import operator

def loadDataset(filename, split, trainingSet=[] , testSet=[]):

	with open(filename, 'rb') as csvfile:

	    lines = csv.reader(csvfile)
	    dataset = list(lines)

	    for x in range(len(dataset)-1):

	        for y in range(4):
	            dataset[x][y] = float(dataset[x][y])

	        if random.random() < split:
	            trainingSet.append(dataset[x])

	        else:
	            testSet.append(dataset[x])


def euclideanDistance(instance1, instance2, length):

	distance = 0
	for x in range(length):
		distance += pow((instance1[x] - instance2[x]), 2)
	return math.sqrt(distance)


def getNeighbors(trainingSet, testInstance, k):

	distances = []
	length = len(testInstance)-1
	for x in range(len(trainingSet)):
		dist = euclideanDistance(testInstance, trainingSet[x], length)
		distances.append((trainingSet[x], dist))
	distances.sort(key=operator.itemgetter(1))
	neighbors = []
	for x in range(k):
		neighbors.append(distances[x][0])
	return neighbors


def getResponse(neighbors):

	classVotes = {}

	for x in range(len(neighbors)):
		response = neighbors[x][-1]
		if response in classVotes:
			classVotes[response] += 1
		else:
			classVotes[response] = 1
	sortedVotes = sorted(classVotes.iteritems(), key=operator.itemgetter(1), reverse=True)
	return sortedVotes[0][0]

def getAccuracy(testSet, predictions):
	correct = 0
	for x in range(len(testSet)):
		if testSet[x][-1] == predictions[x]:
			correct += 1
	return (correct/float(len(testSet))) * 100.0
	


def knn_main():
	# prepare data
	trainingSet=[]
	testSet=[]
	split = 0.67
	loadDataset('iris.data', split, trainingSet, testSet)
	print 'Train set: ' + repr(len(trainingSet))
	print 'Test set: ' + repr(len(testSet))
	# generate predictions
	predictions=[]
	k = 3
	for x in range(len(testSet)):
		neighbors = getNeighbors(trainingSet, testSet[x], k)
		result = getResponse(neighbors)
		predictions.append(result)
		print('> predicted=' + repr(result) + ', actual=' + repr(testSet[x][-1]))
	accuracy = getAccuracy(testSet, predictions)
	print('Accuracy: ' + repr(accuracy) + '%')
	
knn_main()
# END of k-nearest neighbours algorithm
# -------------------------------------








# k - means algorithm
# -------------------



# solution -a 
# -----------


# Function: K Means
# -------------
# K-Means is an algorithm that takes in a dataset and a constant
# k and returns k centroids (which define clusters of data in the
# dataset which are similar to one another).

def kmeans(dataSet, k):
	
    # Initialize centroids randomly
    numFeatures = dataSet.getNumFeatures()
    centroids = getRandomCentroids(numFeatures, k)
    
    # Initialize book keeping vars.
    iterations = 0
    oldCentroids = None
    
    # Run the main k-means algorithm
    while not shouldStop(oldCentroids, centroids, iterations):
        # Save old centroids for convergence test. Book keeping.
        oldCentroids = centroids
        iterations += 1
        
        # Assign labels to each datapoint based on centroids
        labels = getLabels(dataSet, centroids)
        
        # Assign centroids based on datapoint labels
        centroids = getCentroids(dataSet, labels, k)
        
    # We can get the labels too by calling getLabels(dataSet, centroids)
    return centroids
# Function: Should Stop
# -------------
# Returns True or False if k-means is done. K-means terminates either
# because it has run a maximum number of iterations OR the centroids
# stop changing.
def shouldStop(oldCentroids, centroids, iterations):
    if iterations > MAX_ITERATIONS: return True
    return oldCentroids == centroids
# Function: Get Labels
# -------------
# Returns a label for each piece of data in the dataset. 
def getLabels(dataSet, centroids):
    # For each element in the dataset, chose the closest centroid. 
    # Make that centroid the element's label.
# Function: Get Centroids
# -------------
# Returns k random centroids, each of dimension n.
def getCentroids(dataSet, labels, k):
    pass
    # Each centroid is the geometric mean of the points that
    # have that centroid's label. Important: If a centroid is empty (no points have
    # that centroid's label) you should randomly re-initialize it.

def


x = np.array([1, 2, 3, 4, 5]
y = np.array([8, 8, 8, 8, 8])
z = np.ones((5, 9))

np.sqrt(sum((x - y) ** 2))
np.sqrt(((z-x)**2).sum(axis=0))



# END of solution -a 
# ------------------


# solution -b 
# -----------

# Lloyd’s algorithm for performing k-means clustering

import numpy as np

 
def cluster_points(X, mu):
    clusters  = {}
    for x in X:
        bestmukey = min([(i[0], np.linalg.norm(x-mu[i[0]])) \
                    for i in enumerate(mu)], key=lambda t:t[1])[0]
        try:
            clusters[bestmukey].append(x)
        except KeyError:
            clusters[bestmukey] = [x]
    return clusters

 
def reevaluate_centers(mu, clusters):
    newmu = []
    keys = sorted(clusters.keys())
    for k in keys:
        newmu.append(np.mean(clusters[k], axis = 0))
    return newmu
 

def has_converged(mu, oldmu):
    return (set([tuple(a) for a in mu]) == set([tuple(a) for a in oldmu])
 
def find_centers(X, K):
    # Initialize to K random centers
    oldmu = random.sample(X, K)
    mu = random.sample(X, K)
    while not has_converged(mu, oldmu):
        oldmu = mu
        # Assign all points in X to clusters
        clusters = cluster_points(X, mu)
        # Reevaluate centers
        mu = reevaluate_centers(oldmu, clusters)
    return(mu, clusters)


#############
import random
 
def init_board(N):
    X = np.array([(random.uniform(-1, 1), random.uniform(-1, 1)) for i in range(N)])
    return X

def init_board_gauss(N, k):
    n = float(N)/k
    X = []
    for i in range(k):
        c = (random.uniform(-1, 1), random.uniform(-1, 1))
        s = random.uniform(0.05,0.5)
        x = []
        while len(x) < n:
            a, b = np.array([np.random.normal(c[0], s), np.random.normal(c[1], s)])
            # Continue drawing points from the distribution in the range [-1,1]
            if abs(a) < 1 and abs(b) < 1:
                x.append([a,b])
        X.extend(x)
    X = np.array(X)[:N]
    return X


# END of solution - b
# -------------------




# soluton - c // better implementation 
# -----------
# Full Imports

import sys
import math
import random
import subprocess

"""
To use plotly integration you will need to:
1. Get a username/key from www.plot.ly/api and enter them below
2. Install the plotly module: pip install plotly
"""

PLOTLY_USERNAME = None
PLOTLY_KEY = None

if PLOTLY_USERNAME:
    from plotly import plotly

def main():
    
    # How many points are in our dataset?
    num_points = 10
    
    # For each of those points how many dimensions do they have?
    dimensions = 2
    
    # Bounds for the values of those points in each dimension
    lower = 0
    upper = 200
    
    # The K in k-means. How many clusters do we assume exist?
    num_clusters = 3
    
    # When do we say the optimization has 'converged' and stop updating clusters
    opt_cutoff = 0.5
    
    # Generate some points
    points = [makeRandomPoint(dimensions, lower, upper) for i in xrange(num_points)]
    
    # Cluster those data!
    clusters = kmeans(points, num_clusters, opt_cutoff)

    # Print our clusters
    for i,c in enumerate(clusters):

        for p in c.points:
            print " Cluster: ", i, "\t Point :", p
    
    # Display clusters using plotly for 2d data
    # This uses the 'open' command on a URL and may only work on OSX.
    if dimensions == 2 and PLOTLY_USERNAME:

        print "Plotting points, launching browser ..."
        plotClusters(clusters)

class Point:
    '''
    An point in n dimensional space
    '''
    def __init__(self, coords):
        '''
        coords - A list of values, one per dimension
        '''
        
        self.coords = coords
        self.n = len(coords)
        
    def __repr__(self):
        return str(self.coords)

class Cluster:
    '''
    A set of points and their centroid
    '''
    
    def __init__(self, points):
        '''
        points - A list of point objects
        '''

        if len(points) == 0: 
        	raise Exception("ILLEGAL: empty cluster")
        # The points that belong to this cluster
        self.points = points
        
        # The dimensionality of the points in this cluster
        self.n = points[0].n

        # Assert that all points are of the same dimensionality
        for p in points:
            if p.n != self.n: 
            	raise Exception("ILLEGAL: wrong dimensions")
            
        # Set up the initial centroid (this is usually based off one point)
        self.centroid = self.calculateCentroid()
        
    def __repr__(self):
        '''
        String representation of this object
        '''
        return str(self.points)
    
    def update(self, points):
        '''
        Returns the distance between the previous centroid and the new after
        recalculating and storing the new centroid.
        '''
        old_centroid = self.centroid
        self.points = points
        self.centroid = self.calculateCentroid()
        shift = getDistance(old_centroid, self.centroid) 
        return shift
    
    def calculateCentroid(self):
        '''
        Finds a virtual center point for a group of n-dimensional points
        '''
        numPoints = len(self.points)
        # Get a list of all coordinates in this cluster
        coords = [p.coords for p in self.points]
        # Reformat that so all x's are together, all y'z etc.
        unzipped = zip(*coords)
        # Calculate the mean for each dimension
        centroid_coords = [math.fsum(dList)/numPoints for dList in unzipped]        
        return Point(centroid_coords)


def kmeans(points, k, cutoff):
    
    # Pick out k random points to use as our initial centroids
    initial = random.sample(points, k)
    
    # Create k clusters using those centroids
    clusters = [Cluster([p]) for p in initial]
    
    # Loop through the dataset until the clusters stabilize
    loopCounter = 0

    while True:
        # Create a list of lists to hold the points in each cluster
        lists = [ [] for c in clusters]
        clusterCount = len(clusters)
        
        # Start counting loops
        loopCounter += 1
        # For every point in the dataset ...
        for p in points:
            # Get the distance between that point and the centroid of the first
            # cluster.
            smallest_distance = getDistance(p, clusters[0].centroid)
        
            # Set the cluster this point belongs to
            clusterIndex = 0
        
            # For the remainder of the clusters ...
            for i in range(clusterCount - 1):
                # calculate the distance of that point to each other cluster's
                # centroid.
                distance = getDistance(p, clusters[i+1].centroid)
                # If it's closer to that cluster's centroid update what we
                # think the smallest distance is, and set the point to belong
                # to that cluster
                if distance < smallest_distance:
                    smallest_distance = distance
                    clusterIndex = i+1
            lists[clusterIndex].append(p)
        
        # Set our biggest_shift to zero for this iteration
        biggest_shift = 0.0
        
        # As many times as there are clusters ...
        for i in range(clusterCount):
            # Calculate how far the centroid moved in this iteration
            shift = clusters[i].update(lists[i])
            # Keep track of the largest move from all cluster centroid updates
            biggest_shift = max(biggest_shift, shift)
        
        # If the centroids have stopped moving much, say we're done!
        if biggest_shift < cutoff:
            print "Converged after %s iterations" % loopCounter
            break
    return clusters

def getDistance(a, b):
    '''
    Euclidean distance between two n-dimensional points.
    Note: This can be very slow and does not scale well
    '''
    if a.n != b.n:
        raise Exception("ILLEGAL: non comparable points")
    
    ret = reduce(lambda x,y: x + pow((a.coords[y]-b.coords[y]), 2),range(a.n),0.0)
    return math.sqrt(ret)

def makeRandomPoint(n, lower, upper):
    '''
    Returns a Point object with n dimensions and values between lower and
    upper in each of those dimensions
    '''
    p = Point([random.uniform(lower, upper) for i in range(n)])
    return p

def plotClusters(data):
    '''
    Use the plotly API to plot data from clusters.
    
    Gets a plot URL from plotly and then uses subprocess to 'open' that URL
    from the command line. This should open your default web browser.
    '''
    
    # List of symbols each cluster will be displayed using    
    symbols = ['circle', 'cross', 'triangle-up', 'square']

    # Convert data into plotly format.
    traceList = []
    for i, c in enumerate(data):
        data = []
        for p in c.points:
            data.append(p.coords)
        # Data
        trace = {}
        trace['x'], trace['y'] = zip(*data)
        trace['marker'] = {}
        trace['marker']['symbol'] = symbols[i]
        trace['name'] = "Cluster " + str(i)
        traceList.append(trace)
        # Centroid (A trace of length 1)
        centroid = {}
        centroid['x'] = [c.centroid.coords[0]]
        centroid['y'] = [c.centroid.coords[1]]
        centroid['marker'] = {}
        centroid['marker']['symbol'] = symbols[i]
        centroid['marker']['color'] = 'rgb(200,10,10)'
        centroid['name'] = "Centroid " + str(i)
        traceList.append(centroid)
    
    # Log in to plotly
    py = plotly(username=PLOTLY_USERNAME, key=PLOTLY_KEY)

    # Style the chart
    datastyle = {'mode':'markers',
             'type':'scatter',
             'marker':{'line':{'width':0},
                       'size':12,
                       'opacity':0.6,
                       'color':'rgb(74, 134, 232)'}}
    
    resp = py.plot(*traceList, style = datastyle)
    
    # Display that plot in a browser
    cmd = "open " + resp['url']
    subprocess.call(cmd, shell=True)

if __name__ == "__main__": 
    main()

# END of soluton - c 
#-------------------





# solution -d
# -----------

import os
import numpy as np

# kmeans clustering algorithm
# data = set of data points
# k = number of clusters
# c = initial list of centroids (if provided)
#
def kmeans(data, k, c):
    centroids = []

    centroids = randomize_centroids(data, centroids, k)  

    old_centroids = [[] for i in range(k)] 

    iterations = 0
    while not (has_converged(centroids, old_centroids, iterations)):
        iterations += 1

        clusters = [[] for i in range(k)]

        # assign data points to clusters
        clusters = euclidean_dist(data, centroids, clusters)

        # recalculate centroids
        index = 0
        for cluster in clusters:
            old_centroids[index] = centroids[index]
            centroids[index] = np.mean(cluster, axis=0).tolist()
            index += 1


    print("The total number of data instances is: " + str(len(data)))
    print("The total number of iterations necessary is: " + str(iterations))
    print("The means of each cluster are: " + str(centroids))
    print("The clusters are as follows:")
    for cluster in clusters:
        print("Cluster with a size of " + str(len(cluster)) + " starts here:")
        print(np.array(cluster).tolist())
        print("Cluster ends here.")

    return

# Calculates euclidean distance between
# a data point and all the available cluster
# centroids.      
def euclidean_dist(data, centroids, clusters):
    for instance in data:  
        # Find which centroid is the closest
        # to the given data point.
        mu_index = min([(i[0], np.linalg.norm(instance-centroids[i[0]])) \
                            for i in enumerate(centroids)], key=lambda t:t[1])[0]
        try:
            clusters[mu_index].append(instance)
        except KeyError:
            clusters[mu_index] = [instance]

    # If any cluster is empty then assign one point
    # from data set randomly so as to not have empty
    # clusters and 0 means.        
    for cluster in clusters:
        if not cluster:
            cluster.append(data[np.random.randint(0, len(data), size=1)].flatten().tolist())

    return clusters


# randomize initial centroids
def randomize_centroids(data, centroids, k):
    for cluster in range(0, k):
        centroids.append(data[np.random.randint(0, len(data), size=1)].flatten().tolist())
    return centroids


# check if clusters have converged    
def has_converged(centroids, old_centroids, iterations):
    MAX_ITERATIONS = 1000
    if iterations > MAX_ITERATIONS:
        return True
    return old_centroids == centroids


# END of solution - d
# -------------------




# solution -e 
# -----------


# k-means clustering method to cluster image pixels into k partitions.
# Method takes input RGB  image and k value, returns a binary image which has
# different labels for different segments. In the output image, each
# input pixel value is replaced with the mean value it is assigned to.

import os
from random import randint
import time

from skimage import io
import numpy as np
import matplotlib.pyplot as plt


# creates RGB pixel object from image pixels
class RGB:
    def __init__(self, rgb_pixel):
        self.r = int(rgb_pixel[0])
        self.g = int(rgb_pixel[1])
        self.b = int(rgb_pixel[2])

    def __str__(self):
        return ''.join("r:" + str(self.r) + " g:" + str(self.g) + " b:" + str(self.b))


# creates Centroid object from coordinates and rgb pixel
class Centroid:
    x = 0
    y = 0
    rgb = RGB(np.zeros(3))

    def __init__(self, xy):
        self.x = xy[0]
        self.y = xy[1]

    def __str__(self):
        return ''.join("x:" + str(self.x) + " y:" + str(self.y) + " rgb:" + str(self.rgb))


# calculate_rgb_distance(pixel, centroid)
# input     - image and centroid Pixel(r,g,b)
# returns   - euclidean distance between two rgb pixels
def calculate_rgb_distance(pixel, center):
    p1 = np.array((pixel.r, pixel.g, pixel.b))
    p2 = np.array((center.r, center.g, center.b))
    return np.linalg.norm(p1 - p2)


# pick_random_xy(shape)
# input     - RGB image matrix shape (rows, columns, rgb)
# returns   - random pixel(x,y) coordinates
def pick_random_xy(shape):
    return [randint(0, shape[0] - 1), randint(0, shape[1] - 1)]


# k_means(image, k)
# input     - rgb image matrix
#           - k number of clusters
#           - difference threshold to stop the algorithm loop(optional)
# returns   - labelImage, labeled image matrix
#           - centroids, k centroids after calculation
def k_means(image, cluster_count, mean_diff_threshold=5.0):
    time_start = time.time()

    # create a matrix of zeros with the same shape to store centroid index assignments for each pixel
    label_image = np.zeros((image.shape[0], image.shape[1]))
    print "label count k:", k
    print "total mean diff threshold:", mean_diff_threshold

    # k-means algorithm
    # STEPS:
    # 0 - Choose k random centroid coordinates
    # For each pixel x:
    # 1 - Calculate rgb distance of x from each centroid
    # 2 - Assign x to closest centroid's cluster
    # For each centroid c:
    # 3 - Calculate rgb means of assigned pixels for that c (move the centroid)
    # repeat until all x are stable

    # 0 - Choose k random centroid coordinates
    centroids = []
    for i in range(cluster_count):
        c = Centroid(pick_random_xy(image.shape))   # set random coordinates
        c.rgb = RGB(image[c.x][c.y])                # set rgb values
        centroids.append(c)

    iteration_count = 0

    while iteration_count < 10:         # repeat until all x are stable, max 10 iterations

        percent_diff = 0.0              # holds percentage in mean change

        # k x 3 dimensional array to hold cluster values, will be used to calculate new means
        cluster_values = np.ones((len(centroids), 3))
        cluster_pixel_counts = np.ones((len(centroids)))

        # 1 - Calculate rgb distance of x from each centroid
        for x in xrange(image.shape[0]):
            for y in xrange(image.shape[1]):

                image_pixel = RGB(image[x][y])
                min_dist = 255
                min_dist_index = 0

                for idx, m in enumerate(centroids):

                    distance = calculate_rgb_distance(image_pixel, m.rgb)

                    if distance < min_dist:
                        min_dist = distance
                        min_dist_index = idx

                # 2 - Assign pixel to closest centroid's cluster(index)
                label_image[x][y] = min_dist_index

                # record pixel values to calculate means
                cluster_values[min_dist_index][0] += image_pixel.r
                cluster_values[min_dist_index][1] += image_pixel.g
                cluster_values[min_dist_index][2] += image_pixel.b
                cluster_pixel_counts[min_dist_index] += 1

        # 3 - Calculate rgb means of assigned pixels for their centroids
        for idx, m in enumerate(centroids):
            
            previous_sum = m.rgb.r + m.rgb.g + m.rgb.b
            
            m.rgb.r = int(cluster_values[idx, 0] / cluster_pixel_counts[idx])
            m.rgb.g = int(cluster_values[idx, 1] / cluster_pixel_counts[idx])
            m.rgb.b = int(cluster_values[idx, 2] / cluster_pixel_counts[idx])
            
            current_sum = m.rgb.r + m.rgb.g + m.rgb.b
            
            # total percent change in cluster means
            if previous_sum > 0:
                percent_diff += (abs((current_sum - previous_sum) / float(previous_sum))) * 100.0
            else:
                percent_diff = 0.0

        iteration_count += 1
        print "total percent difference in means", percent_diff, "iteration", iteration_count

        if mean_diff_threshold > percent_diff > 0.0:
            print "\nStopped at percent difference in means:", percent_diff
            print "total iterations:", iteration_count
            print "total time spent:", '%0.3f ms' % ((time.time() - time_start) * 1000.0)
            print "\ncalculated cluster means:"
            for idx, m in enumerate(centroids):
                print centroids[idx].rgb
            break

    return label_image, centroids


# load RGB image, alpha channel removed
fileName = os.path.join('strawberry.png')
inputImage = io.imread(fileName)

# number of clusters/centroids
k = 4

# run k-means algorithm on input
binaryImage, centroid_array = k_means(inputImage, k)

outputImage = np.zeros_like(inputImage)

# apply cluster centroid means to assigned pixels
for x1 in xrange(binaryImage.shape[0]):
    for y1 in xrange(binaryImage.shape[1]):
        
        outputImage[x1][y1][0] = centroid_array[int(binaryImage[x1][y1])].rgb.r
        outputImage[x1][y1][1] = centroid_array[int(binaryImage[x1][y1])].rgb.g
        outputImage[x1][y1][2] = centroid_array[int(binaryImage[x1][y1])].rgb.b


# create plots and save output image
fig = plt.figure()
imgOrig = plt.subplot2grid((2, 2), (0, 0))
imgOrig.imshow(inputImage, interpolation='nearest')
imgOrig.set_title('Original Image')

imgLabeled = plt.subplot2grid((2, 2), (0, 1))
imgLabeled.imshow(binaryImage, cmap=plt.cm.gray, interpolation='nearest')
imgLabeled.set_title('Labeled Image')

imgOutput = plt.subplot2grid((2, 2), (1, 0), colspan=2)
imgOutput.imshow(outputImage, interpolation='nearest')
imgOutput.set_title('Output Image')

plt.savefig('label-' + str(k) + '-output.png')
plt.show()


# END of solution -e
# ------------------




# END of k - means algorithm
# --------------------------