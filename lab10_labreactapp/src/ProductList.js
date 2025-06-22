import React, { useState } from 'react';
import ProductItem from './ProductItem';

const ProductList = ({ products }) => {
  const [filter, setFilter] = useState('');

  return (
    <div>
      <h1>List of products</h1>
      <label>
        Filter by product title:
        <input
          type="text"
          value={filter}
          onChange={(e) => setFilter(e.target.value)}
          style={{ marginLeft: '10px' }}
        />
      </label>
      <ul>
        {products
          .filter(p => p.title.toLowerCase().includes(filter.toLowerCase()))
          .map(product => (
            <ProductItem
              key={product.id}
              id={product.id}
              title={product.title}
              brand={product.brand}
            />
          ))}
      </ul>
    </div>
  );
};

export default ProductList;
