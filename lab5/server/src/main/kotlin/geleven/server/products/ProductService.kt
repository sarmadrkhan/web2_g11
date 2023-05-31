package geleven.server.products

interface ProductService {
  fun getAll(): List<ProductDTO>

  fun getProduct(ean: String): ProductDTO?

}