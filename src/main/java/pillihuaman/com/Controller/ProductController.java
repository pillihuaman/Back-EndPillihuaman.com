package pillihuaman.com.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import pillihuaman.com.Help.Constants;
import pillihuaman.com.Service.ProductService;
import pillihuaman.com.model.response.RespBase;
import pillihuaman.com.security.MyJsonWebToken;
import pillihuaman.com.model.request.ReqBase;
import pillihuaman.com.model.request.ReqProduct;
@RestController
@Tag(name = "Product", description = "")
@RequestMapping("Product/")

public class ProductController {
	@Autowired
	private HttpServletRequest httpServletRequest;
	@Autowired
	private ProductService productService;
	
	@Operation(summary = "Create product", description = "Create product", tags = { "" }, security = {
			@SecurityRequirement(name = Constants.BEARER_JWT) })
	@ApiResponses(value = {
			@ApiResponse(responseCode = Constants.SERVER_200, description = Constants.OPERACION_EXITOSA),
			@ApiResponse(responseCode = Constants.SERVER_400, description = Constants.ERROR_VALIDACION, content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RespBase.class))}),
			@ApiResponse(responseCode = Constants.SERVER_500, description = Constants.ERROR_INTERNO, content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RespBase.class))}) })
	@PostMapping(path = { Constants.BASE_ENDPOINT + "/SaveProduct" }, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<RespBase<Object>> SaveProduct(
			@PathVariable String access,
			@Valid @RequestBody ReqBase<ReqProduct> request){
		
		MyJsonWebToken jwt = (MyJsonWebToken) httpServletRequest.getAttribute("jwt");
		RespBase<Object> response = productService.SaveProduct( jwt,request);
		return ResponseEntity.ok(response);
	}
	
	@Operation(summary = "Create product", description = "Create product", tags = { "" }, security = {
			@SecurityRequirement(name = Constants.BEARER_JWT) })
	@ApiResponses(value = {
			@ApiResponse(responseCode = Constants.SERVER_200, description = Constants.OPERACION_EXITOSA),
			@ApiResponse(responseCode = Constants.SERVER_400, description = Constants.ERROR_VALIDACION, content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RespBase.class))}),
			@ApiResponse(responseCode = Constants.SERVER_500, description = Constants.ERROR_INTERNO, content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RespBase.class))}) })
	@GetMapping(path = { Constants.BASE_ENDPOINT + "/listProduct" }, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<RespBase<Object>> GetProduct(
			@PathVariable String access,
			@Valid @RequestBody ReqBase<ReqProduct> request){
		
		MyJsonWebToken jwt = (MyJsonWebToken) httpServletRequest.getAttribute("jwt");
		RespBase<Object> response = productService.SaveProduct( jwt,request);
		return ResponseEntity.ok(response);
	}
	

	     
	    @GetMapping("/users")
	    public String listAll() {
	        //List<User> listUsers = repo.findAll();
	        //model.addAttribute("listUsers", listUsers);
	        return "users";
	    }
	    
	    
	    
	/*@PostMapping("/SaveProduct")
	public ProductoResponse SaveProduct(@RequestBody Product pro) {
		UserServiceImpl WebService = new UserServiceImpl();
		return WebService.SaveProduct(pro);
	}

	@PostMapping("/ListProduct")
	public ProductoResponse ListProduct(@RequestBody String keySearch) {
		ProductRepository WebService = new ProductRepository();
		return WebService.ListProduct();
	}

	@RequestMapping(path = "/savefile", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE,
			MediaType.APPLICATION_OCTET_STREAM_VALUE })
	public ResponseEntity handleFileUpload(@RequestPart("id") String id, @RequestPart("file") MultipartFile file) {
		List<String> files = new ArrayList<String>();
		// private final Path rootLocation = Paths.get("_Path_To_Save_The_File");
		String message;
		try {

			files.add(file.getOriginalFilename());

			message = "Successfully uploaded!";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "Failed to upload!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		} 
	}
	@RequestMapping(path = "/HomeProductIns", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE,
			MediaType.APPLICATION_OCTET_STREAM_VALUE })
	//@PostMapping(path = "/HomeProductIns", consumes = "application/json", produces = "application/json")
	public HomeViewModelResponse HomeProductIns(@RequestPart("id") String id, @RequestPart("file") MultipartFile file) {
		ProductRepository WebService = new ProductRepository();
		HomeViewModel homeview= new  HomeViewModel();
		Imagen img= new Imagen();
		HomeViewModel convertedObject = new Gson().fromJson(id, HomeViewModel.class);
		img=convertedObject.getImagen();
		img.setImagendata(ConvertClass.ConverMultipartFileToByteArray(file));
		convertedObject.setImagen(img);
		// HomeViewModel homeViewModel = fromJsonString(companyJsonStr,
		// HomeViewModel.class);
		return WebService.HomeProductIns(convertedObject);
	}
	@RequestMapping(path = "/RegisterDetailImagen", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE,
			MediaType.APPLICATION_OCTET_STREAM_VALUE })
	//@PostMapping(path = "/HomeProductIns", consumes = "application/json", produces = "application/json")
	public HomeViewModelResponse RegisterDetailImagen(@RequestPart("id") String id,
			@RequestPart("filep") MultipartFile filep,@RequestPart("fileE") MultipartFile fileE,
			@RequestPart("fileD") MultipartFile fileD) {
		ProductRepository WebService = new ProductRepository();
		Detailimagen detailImagen= new  Detailimagen();
		 if (id != null &&  ! id.isEmpty())
		 {
			detailImagen.setIdImagen(Integer.parseInt(id));
			 if(filep!= null)
			 {
				 detailImagen.setImagendata( ConvertClass.ConverMultipartFileToByteArray(filep));
				 WebService.RegisterDetailImagen(detailImagen);
			 }
			 if(fileE!= null)
			 {
				 detailImagen.setImagendata( ConvertClass.ConverMultipartFileToByteArray(fileE));
				 WebService.RegisterDetailImagen(detailImagen);
			 }
			 if(fileD!= null)
			 {
				 detailImagen.setImagendata( ConvertClass.ConverMultipartFileToByteArray(fileD));
				 WebService.RegisterDetailImagen(detailImagen);
			 } 
		 }
		return WebService.HomeProductIns(null);
	}

	@PostMapping(path = "/ListDetImagenByIdProduct", consumes = "application/json", produces = "application/json")
	public List<HomeViewModel> ListDetImagenByIdProduct(@RequestBody Product pro) {
		ProductRepository WebService = new ProductRepository();
		return WebService.ListDetImagenByIdProduct(pro);
	}

	@PostMapping(path = "/ProductoSel", consumes = "application/json", produces = "application/json")
	public ProductoResponse ProductoSel(@RequestBody Product pro) {
		ProductRepository WebService = new ProductRepository();
		return WebService.ProductoSel(pro);
	}

	@PostMapping(path = "/HomeViewModelSelByIdDroducto", consumes = "application/json", produces = "application/json")
	public HomeViewModel HomeViewModelSelByIdDroducto(@RequestBody Product pro) {
		ProductRepository WebService = new ProductRepository();
		return WebService.HomeViewModelSelByIdDroducto(pro);
	}

	@PostMapping(path = "/DetailProductIns", consumes = "application/json", produces = "application/json")
	public boolean DetailProductIns(@RequestBody Detailproduct pro) {
		ProductRepository WebService = new ProductRepository();
		return WebService.DetailProductIns(pro);
	}

	@PostMapping(path = "/ListDetailProductByIdProduct", consumes = "application/json", produces = "application/json")
	public List<Detailproduct> ListDetailProductByIdProduct(@RequestBody Detailproduct pro) {
		ProductRepository WebService = new ProductRepository();
		return WebService.ListDetailProductByIdProduct(pro);
	}
*/
}
