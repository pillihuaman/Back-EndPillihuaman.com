package pillihuaman.com.Service.Implement;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pillihuaman.com.BusinessEntity.model.Product;
import pillihuaman.com.Help.ConvertClass;
import pillihuaman.com.Repository.ProductRepository;
import pillihuaman.com.Service.ProductService;
import pillihuaman.com.model.request.ReqBase;
import pillihuaman.com.model.request.ReqProduct;
import pillihuaman.com.model.response.RespBase;
import pillihuaman.com.security.MyJsonWebToken;

@Component
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Override
	public RespBase<Object> SaveProduct(MyJsonWebToken token, ReqBase<ReqProduct> request) {
		RespBase<Object> response=new RespBase<Object>();
		try {
			request.getData();
			Product tblproduct = new Product();
			tblproduct = ConvertClass.ProductDtoToProductTbl(request.getData());
			tblproduct.setSegIns("User-dmin", Instant.now());
			productRepository.save(tblproduct);
			response.getStatus().setSuccess(Boolean.TRUE);
			response.setPayload("OK");
		} catch (Exception e) {
			response.getStatus().setSuccess(Boolean.FALSE);
			response.getStatus().getError().getMessages().add(e.getMessage());
		}

		return response;
	}

}
