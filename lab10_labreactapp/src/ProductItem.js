import React from 'react';
import { Link } from 'react-router-dom';

const ProductItem = ({ title, brand, id }) => {
  return (
    <li>
      <Link to={`/details/${id}`}>{title}</Link> ({brand})
    </li>
  );
};

export default ProductItem;
