package api.EndPoints;

public class Routes {
	
	public static String base_Url="https://petstore.swagger.io/v2";
	
	// User Base URL
	
	public static String post_Url=base_Url+"/user";
	public static String get_Url=base_Url+"/user/{username}";
	public static String Put_Url=base_Url+"/user/{username}";
	public static String Delete_Url=base_Url+"/user/{username}";
	
	// Store Request URL's
	
	public static String storePostURl=base_Url+"/store/order";
	public static String storeGetURl=base_Url+"/store/order/{orderId}";
	public static String storeDeleteURl=base_Url+"/store/order/{orderId}";
	
	// Pet Request URL's
	
	public static String petPostURl=base_Url+"/pet";
	public static String petgetURl=base_Url+"/pet/{petId}";
	public static String petPostUploadImageURl=base_Url+"/pet/{petId}/uploadImage";
	public static String petUpdateURl=base_Url+"/pet";
	public static String petDeleteURl=base_Url+"/pet/{petId}";


}
