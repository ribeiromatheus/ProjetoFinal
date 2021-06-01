import express from 'express';

import ProductController from './controllers/ProductController';
import CategoryController from './controllers/CategoryController';

const routes = express.Router();

const productController = new ProductController();
const categoryController = new CategoryController();

routes.get('/product', productController.index);
routes.post('/product', productController.create);

routes.get('/category', categoryController.index);
routes.post('/category', categoryController.create);

export default routes;