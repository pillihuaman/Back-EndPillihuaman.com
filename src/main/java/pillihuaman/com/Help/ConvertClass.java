package pillihuaman.com.Help;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import pillihuaman.com.BusinessEntity.model.Product;
import pillihuaman.com.model.request.ReqProduct;
import pillihuaman.com.model.response.RespProduct;

public class ConvertClass {
	private static byte[] byteArray;
	private File file;
	private MultipartFile mutiparfile;

	public static byte[] ConverMultipartFileToByteArray(MultipartFile multipart) {
		try {
			byteArray = multipart.getBytes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return byteArray;
	}
	
	
public  RespProduct	ProductTbtToProductDto(Product product){
	return null;
	
	
}
public static  Product	ProductDtoToProductTbl(ReqProduct request){
	Product resp= new Product();
	resp.setDescription(request.getDescription());
	resp.setExpirationDate(request.getExpirationDate());
	resp.setIdImagen(request.getIdImagen());
	resp.setIdPrice(request.getIdPrice());
	resp.setIdProduct(request.getIdProduct());
	resp.setIdSystem(request.getIdSystem());
	resp.setIdType(request.getIdType());
	resp.setIdUser(request.getIdUser());
	resp.setName(request.getName());
	return resp;
	
}
}
