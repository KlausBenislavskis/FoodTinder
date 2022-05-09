# FoodTinder
Android app made with java, for the AND assingment.
With functionality simillar to Tinder application where people can add each other to friends and check firends favourite recipes.
Recipes are added to favorites via swiping function after searching for dish,ingredient, etc.

Recipes are requested via API from https://api.edamam.com/ , API is limited to 10 request per minute,
Friends and users are authenticated via Firebase google authentication,
user data such as recipes, friends are stored in real time database in Firebase

Requirements:
As a user, I want to be able to browse through different recipes by swiping in different directions, so I can choose a recipe that I like. (Done)
As a user, I want to see all my favorite recipes from before, such that I don't need to go through all of them again.  (Done)
As a user, I want to be able to create an account and log into it, such that I can access my specific account details such as profile, favorite recipe list and friends list. (Done)
As a user I want to be able to add my friends into my friends list such that I can exchange recipes with them. (Done)
As a user, I want to be able to browse through my friends' favorite list, such that I can see what recipes they like.(Done)
As a user I want to be able to add recipes I like into my favorite list, such that I can access them later again more easily. (Done)
As a user I want to be able to search by different ingredients and resulting food before browsing, such that I can evade recipes that include ingredients that I don’t want. (done)

API link:
https://developer.edamam.com/edamam-recipe-api
