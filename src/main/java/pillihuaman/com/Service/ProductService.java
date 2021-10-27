package pillihuaman.com.Service;
import pillihuaman.com.model.request.ReqBase;
import pillihuaman.com.model.request.ReqProduct;
import pillihuaman.com.model.response.RespBase;
import pillihuaman.com.security.MyJsonWebToken;

public interface ProductService {
	RespBase<Object> SaveProduct(MyJsonWebToken token, ReqBase<ReqProduct> request);


}
