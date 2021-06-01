import { Knex } from 'knex';

export async function up(knex: Knex) {
  return knex.schema.createTable('category', table => {
    table.increments('id').primary();
    table.string('category_name').notNullable();
  });
}

export async function down(knex: Knex) {
  return knex.schema.dropTable('category');
}