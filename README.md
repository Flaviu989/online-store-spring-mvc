# SDA Online Store - Spring MVC

## Description
An application that allows adding products to the store through the administration panel. Users are able to register, login as well as add products to cart and place an order.

* [Application UML](https://github.com/Flaviu989/online-store-spring-mvc/blob/master/JDL-Studio%20UML.png)
* [Database Script](https://github.com/Flaviu989/online-store-spring-mvc/blob/master/sda_online_store_schema.sql)

## Functionality
### Main functions
* **Login panel** ✔
* **Admin**: ✔
	* *Adding categories for products*
		* category name ✔
		* subcategory ✔
	* *Cathegory list + edition*
		* button to edit given category ✔
		* button to delete given category ✔
	* *Product list + edition*
		* list all the added products ✔
		* button to edit the given product ✔
		* button to delete the given product ✔
	* *Adding a product*
		* name ✔
		* description  ✔
		* image url ✔
		* price ✔
		* product supplier (dropdown) ✔
		* category (dropdown) ✔
	* *User list + edition*
	    * button to edit given user ✔
	    * button to delete given user (except admin) ✔
	* *Order list + edition*
	    * button to edit given order
	    * button to delete given order ✔
* **User**:
	* *Registration*
		* entering date into form fields + validation of these fields ✔
	* *Logign into*
		* possibility for the user to log in (after registration) ✔
		* possibility for the user to log out ✔
	* *Product list*
		* product table with pagination  ✔
		    * sorting products in the table ✔
		* poduct search ✔
		* adding product to cart ✔
	* *Cart*
	    * display products added to cart ✔
	    * option to orer proucts in the cart -> redirect to user orders ✔

### Aditional functions
    * ability to edit user acount (data) ✔
    * overview of the user orders (from user and admin level) ✔
    * an aestatic and functional way of presenting data ✔
    * pre-validate data collected from user ✔
