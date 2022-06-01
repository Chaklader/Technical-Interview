
	
	It is a mistake when taken in the context that O(n) and O(n log n) functions have better complexity than O(1) and O(log n) functions. When looking typical cases of complexity in big O notation:

						O(1) < O(log n) < O(n) < O(n log n) < O(n^2)

	Notice that this doesn't necessarily mean that they will always be better performance-wise - we could have an O(1) function that takes a long time to execute even though its complexity is unaffected by element count. Such a function would look better in big O notation than an O(log n) function, but could actually perform worse in practice.

	Generally speaking: a function with lower complexity (in big O notation) will outperform a function with greater complexity (in big O notation) when n is sufficiently high.

