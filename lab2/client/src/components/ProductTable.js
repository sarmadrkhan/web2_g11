import React, { useState } from "react";
import { Button, Table } from "react-bootstrap";

const ProductTable = (props) => {
  return (
    <div className="d-flex justify-content-center">
      <Table striped bordered>
        <thead>
          <tr>
            <th>Product List</th>
            <th>Details</th>
          </tr>
        </thead>
        <tbody>
          {props.products.map((product) => {
            return (
              <ProductRow
                key={product.ean}
                product={product}
                handleProductSelect={props.handleProductSelect}
              />
            );
          })}
        </tbody>
      </Table>
    </div>
  );
};

const ProductRow = (props) => {
  const [showDetails, setShowDetails] = useState(false);

  const toggleDetails = () => {
    setShowDetails(!showDetails);
  };

  return (
    <tr key={props.product.ean}>
      <td>{props.product.name}</td>
      <td>
        {showDetails && (
          <div className="border rounded">
            <p>
              <strong>Product Details</strong>
              <br />
              <strong>EAN:</strong> {props.product.ean}
              <br />
              <strong>Name:</strong> {props.product.name}
              <br />
              <strong>Brand:</strong> {props.product.brand}
            </p>
            <Button onClick={toggleDetails}>Hide Details</Button>
          </div>
        )}
        {!showDetails && <Button onClick={toggleDetails}>Show Details</Button>}
      </td>
    </tr>
  );
};

export default ProductTable;
