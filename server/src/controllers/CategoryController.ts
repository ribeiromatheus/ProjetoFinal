import { Request, Response } from 'express';

import knex from '../database/connection';

export default class CategoryController {
  async index(request: Request, response: Response) {
    const categories = await knex('category').select('*');

    return response.json(categories);
  }

  async create(request: Request, response: Response) {
    const { category_name } = request.body;

    const [id] = await knex('category').insert({ category_name });

    return response.json({ id });
  }
}