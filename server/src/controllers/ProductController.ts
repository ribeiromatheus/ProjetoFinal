import { Request, Response } from 'express';

import knex from '../database/connection';

export default class ProductController {
  async index(request: Request, response: Response) {
    const products = await knex('product')
      .join('category', 'product.category', '=', 'category.id')
      .select('product.product_name', 'product.price', 'category.category_name');

    return response.json(products);
  }

  async create(request: Request, response: Response) {
    const { product_name, price, category } = request.body;

    const [id] = await knex('product').insert({ product_name, price, category });

    return response.json({ id });
  }
}