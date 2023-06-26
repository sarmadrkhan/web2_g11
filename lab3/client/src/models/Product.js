/**
 *
 * @param {number} ean European Article Number that identifies a Product
 * @param {string} name name of the product
 * @param {string} brand brand of the product
 */
function Product({ ean, name, brand } = {}) {
  this.ean = ean;
  this.name = name;
  this.brand = brand;
}

exports.Product = Product;
