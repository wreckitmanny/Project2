import java.io.*;
import java.util.*;

public class Whataburger {

	public static void main(String[] args) throws IOException {
		List<Store> stores = readStores("WhataburgerData.csv");
		List<Query> queries = readQueries("Queries.csv");
		
		for (Query query : queries) {
			for (Store store : stores) {
				store.computeDistance(query.latitude, query.longitude);
			}
		}
		Store[] storesArray = stores.toArray(new Store[0]);
		
		int a = Query.num;
		if(a > storesArray.length) {
			a = storesArray.length;
		}
		Store closestStore = randSelect(storesArray, 0, storesArray.length - 1, a);
		Arrays.sort(storesArray, Comparator.comparingDouble(s -> s.distance));
		
		System.out.println("The " + a + " closest stores to (" + query.latitude + "," query.longitude + "):");
		for (int i = 0; i < k; i++) {
			System.out.println(storesArray[i].id + " - " + storesArray[i].address + " - " + stores.toArray[i].distance + " miles");
		}
		System.out.println();
	}
	
	public static ArrayList<Query> readQueries(String file) {
		ArrayList<Query> queries = new ArrayList<>();
		try (BufferedReader buffer = new BufferedReader(new FileReader(file))){
			String line;
				while((line = buffer.readLine()) != null) {
					String[] section = line.split(",");
					double latitude = Double.parseDouble(section[0]);
					double longtitude = Double.parseDouble(section[1]);
					int num = Integer.parseInt(section[2]);
					queries.add(new Query(latitude, longtitude, num));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return queries;
	}
	
	public static ArrayList<Store> readStores(String file){
		ArrayList<Store> stores = new ArrayList<>();
		try (BufferedReader buffer = new BufferedReader(new FileReader(file))){
			String line;
			buffer.readLine();
			while ((line = buffer.readLine()) != null) {
				String[] section = line.split(",");
				int id = Integer.parseInt(section[0]);
				String address = section[1];
				String city = section[2];
				String state = section[3];
				String zip = section[4];
				double latitude = Double.parseDouble(section[5]);
				double longitude = Double.parseDouble(section[6]);
				stores.add(new Store(id, address, city, state, zip, latitude, longitude));
			}
		}
		
		return stores;
	}
	
	public static class Query {
		double latitude;
		double longitude;
		int num;
		
		public Query(double latitude, double longitude, int num) {
			this.latitude = latitude;
			this.longitude = longitude;
			this.num = num;
		}
	}
	public static class Store {

		public String id;
		public String address;
		public String city;
		public String state;
		public String zipCode;
		public double latitude;
		public double longitude;
		public double distance = -1;
		
		public Store(String theID, String theAddress, String theCity, String theState, String theZip, double theLat, double theLong) {
			
			id = theID;
			address = theAddress;
			city = theCity;
			state = theState;
			zipCode = theZip;
			latitude = theLat;
			longitude = theLong;
		}
	
	public void computeDistance(double otherLat, double otherLong) {
		
		//Haversine Formula
		double radiusOfEarthInMiles = 3958.8;
		
		//First we convert the latitudes and longitudes to radians.
		double lat1 = Math.toRadians(latitude);
		double lat2 = Math.toRadians(otherLat);
		double long1 = Math.toRadians(longitude);
		double long2 = Math.toRadians(otherLong);
		
		//Then we can apply the Haversine Formula to get the distance in miles.
		double a = Math.pow(Math.sin((lat2-lat1)/2), 2) + Math.cos(lat1)*Math.cos(lat2)*Math.pow(Math.sin((long2-long1)/2), 2);
		double c = 2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		distance = radiusOfEarthInMiles*c;
		
	}
	
	public static Store randSelect(Store[] stores, int left, int right, int x) {
		if (left == right) {
			return stores[left];
		}
		int pivot = randPartition(stores, left, right);
		int length = pivot - left + 1;
		if (x == length) {
			return stores[pivot];
		} else if (x < length) {
			return randSelect(stores, left, pivot -1, x);
		} else {
			return randSelect(stores, pivot + 1, right, x - length);
		}
	}
	
	public static int randPartition(Store[] stores, int left, int right) {
		int pivotI = left + (int)(Math.random() * (right - left + 1));
		double pivotV = stores[pivotI].distance;
		swap(stores, pivotI, right);
		int storeI = left;
		for (int i = left; i <= right; i++) {
			if (stores[i].distance < pivotV){
				swap(stores, i, storeI);
				storeI++;
			}
		}
		swap(stores, storeI, right);
		return storeI;
	}
	
	public static void swap(Store[] stores, int i, int j) {
		Store temp = stores[i];
		stores[i] = stores[j];
		stores [j] = temp;

	}

	}
}
