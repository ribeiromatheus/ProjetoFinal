import { Knex } from 'knex';

export async function up(knex: Knex) {
  return knex.schema.createTable('product', table => {
    table.increments('id').primary();
    table.string('product_name').notNullable();
    table.decimal('price').notNullable();
    table.integer('category')
      .notNullable()
      .references('id')
      .inTable('category');
  });
}

export async function down(knex: Knex) {
  return knex.schema.dropTable('product');
}