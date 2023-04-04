package geleven.server.products

import geleven.server.utils.NotFoundException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
  private val productService: ProductService
) {
  @GetMapping("/API/products/")
  fun getAll(): List<ProductDTO> {
    return productService.getAll()
  }

  @GetMapping("/API/products/{ean}")
  fun getProduct(@PathVariable ean: String): ProductDTO? {
    val product = productService.getProduct(ean)
    return product ?: throw NotFoundException("Product with EAN $ean not found")
  }
}