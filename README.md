  One application of the order statistic query algorithm (Rand-Select) that we discussed in
class is finding the locations that are nearby a given query point. So, for example, if we are
working for a company that has many stores across the country, we may want to help a user
find the stores that are closest to their current location (which in practice might be provided by
their cell phone). This can be done by computing the distance from the user to all the stores,
then performing an order statistics query to find the stores that are closest to the user. As
discussed in class, the expected running time of the order statistic query algorithm is O(n),
where n here would be the number of stores. This would practically be a very fast running time
even if we have thousands of stores to search through.

  In this project, we will perform this task from the perspective of Whataburger and
Starbucks. In this project, you will read in the IDs, addresses, latitude, and longitude of each
store location from a file and save it in an “array-like” data structure (a vector, ArrayList, etc. is
fine as well). You will need to implement the Rand-Select() algorithm we covered in class (as
well as the Rand-Partition() algorithm that is uses). There is a separate file that contains the
query points, one per line. A query consists of three numbers: latitude, longitude, and the
number of stores we want to find. So, a general outline of how to handle the queries is as
follows:


For each query:

• Compute the distance of the query point to each of the different stores using the
Haversine formula. See here for my Java implementation of this formula:
https://youtu.be/Camxl1fTzaw?t=290. The project zip file provides implementations of
the Haversine formula in C, Java, and Python.

• Use the order statistic query algorithm (Rand-Select()) to find the farthest store from
the query that we care about. For example, if we want to report 30 stores, you should
search for the 30th closest store to the query point.

• Now that you know the 30th closest store to the query, you can then find all the other
stores that are at least this close to the query in the indices “to the left” of the store
returned by the algorithm. Sort them in increasing distance from the query point and
print them as I have shown here: https://youtu.be/Camxl1fTzaw?t=808
