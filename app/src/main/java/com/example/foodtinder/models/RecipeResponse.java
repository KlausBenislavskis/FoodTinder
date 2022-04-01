package com.example.foodtinder.models;

public final class RecipeResponse {
    public final long from;
    public final long to;
    public final long count;
    public final _links _links;
    public final Hit hits[];

    public RecipeResponse(long from, long to, long count, _links _links, Hit[] hits){
        this.from = from;
        this.to = to;
        this.count = count;
        this._links = _links;
        this.hits = hits;
    }

    public static final class _links {
        public final Self self;
        public final Next next;

        public _links(Self self, Next next){
            this.self = self;
            this.next = next;
        }

        public static final class Self {
            public final String href;
            public final String title;

            public Self(String href, String title){
                this.href = href;
                this.title = title;
            }
        }

        public static final class Next {
            public final String href;
            public final String title;

            public Next(String href, String title){
                this.href = href;
                this.title = title;
            }
        }
    }

    public static final class Hit {
        public final Recipe recipe;
        public final _links _links;

        public Hit(Recipe recipe, _links _links){
            this.recipe = recipe;
            this._links = _links;
        }

        public static final class Recipe {
            public final String uri;
            public final String label;
            public final String image;
            public final Images images;
            public final String source;
            public final String url;
            public final String shareAs;
            public final long yield;
            public final String[] dietLabels;
            public final String[] healthLabels;
            public final String[] cautions;
            public final String[] ingredientLines;
            public final Ingredient ingredients[];
            public final long calories;
            public final long glycemicIndex;
            public final long totalCO2Emissions;
            public final String co2EmissionsClass;
            public final long totalWeight;
            public final String[] cuisineType;
            public final String[] mealType;
            public final String[] dishType;
            public final TotalNutrients totalNutrients;
            public final TotalDaily totalDaily;
            public final Digest digest[];

            public Recipe(String uri, String label, String image, Images images, String source, String url, String shareAs, long yield, String[] dietLabels, String[] healthLabels, String[] cautions, String[] ingredientLines, Ingredient[] ingredients, long calories, long glycemicIndex, long totalCO2Emissions, String co2EmissionsClass, long totalWeight, String[] cuisineType, String[] mealType, String[] dishType, TotalNutrients totalNutrients, TotalDaily totalDaily, Digest[] digest){
                this.uri = uri;
                this.label = label;
                this.image = image;
                this.images = images;
                this.source = source;
                this.url = url;
                this.shareAs = shareAs;
                this.yield = yield;
                this.dietLabels = dietLabels;
                this.healthLabels = healthLabels;
                this.cautions = cautions;
                this.ingredientLines = ingredientLines;
                this.ingredients = ingredients;
                this.calories = calories;
                this.glycemicIndex = glycemicIndex;
                this.totalCO2Emissions = totalCO2Emissions;
                this.co2EmissionsClass = co2EmissionsClass;
                this.totalWeight = totalWeight;
                this.cuisineType = cuisineType;
                this.mealType = mealType;
                this.dishType = dishType;
                this.totalNutrients = totalNutrients;
                this.totalDaily = totalDaily;
                this.digest = digest;
            }

            public static final class Images {
                public final THUMBNAIL tHUMBNAIL;
                public final SMALL sMALL;
                public final REGULAR rEGULAR;
                public final LARGE lARGE;

                public Images(THUMBNAIL tHUMBNAIL, SMALL sMALL, REGULAR rEGULAR, LARGE lARGE){
                    this.tHUMBNAIL = tHUMBNAIL;
                    this.sMALL = sMALL;
                    this.rEGULAR = rEGULAR;
                    this.lARGE = lARGE;
                }

                public static final class THUMBNAIL {
                    public final String url;
                    public final long width;
                    public final long height;

                    public THUMBNAIL(String url, long width, long height){
                        this.url = url;
                        this.width = width;
                        this.height = height;
                    }
                }

                public static final class SMALL {
                    public final String url;
                    public final long width;
                    public final long height;

                    public SMALL(String url, long width, long height){
                        this.url = url;
                        this.width = width;
                        this.height = height;
                    }
                }

                public static final class REGULAR {
                    public final String url;
                    public final long width;
                    public final long height;

                    public REGULAR(String url, long width, long height){
                        this.url = url;
                        this.width = width;
                        this.height = height;
                    }
                }

                public static final class LARGE {
                    public final String url;
                    public final long width;
                    public final long height;

                    public LARGE(String url, long width, long height){
                        this.url = url;
                        this.width = width;
                        this.height = height;
                    }
                }
            }

            public static final class Ingredient {
                public final String text;
                public final long quantity;
                public final String measure;
                public final String food;
                public final long weight;
                public final String foodId;

                public Ingredient(String text, long quantity, String measure, String food, long weight, String foodId){
                    this.text = text;
                    this.quantity = quantity;
                    this.measure = measure;
                    this.food = food;
                    this.weight = weight;
                    this.foodId = foodId;
                }
            }

            public static final class TotalNutrients {

                public TotalNutrients(){
                }
            }

            public static final class TotalDaily {

                public TotalDaily(){
                }
            }

            public static final class Digest {
                public final String label;
                public final String tag;
                public final String schemaOrgTag;
                public final long total;
                public final boolean hasRDI;
                public final long daily;
                public final String unit;
                public final Sub sub;

                public Digest(String label, String tag, String schemaOrgTag, long total, boolean hasRDI, long daily, String unit, Sub sub){
                    this.label = label;
                    this.tag = tag;
                    this.schemaOrgTag = schemaOrgTag;
                    this.total = total;
                    this.hasRDI = hasRDI;
                    this.daily = daily;
                    this.unit = unit;
                    this.sub = sub;
                }

                public static final class Sub {

                    public Sub(){
                    }
                }
            }
        }

        public static final class _links {
            public final Self self;
            public final Next next;

            public _links(Self self, Next next){
                this.self = self;
                this.next = next;
            }

            public static final class Self {
                public final String href;
                public final String title;

                public Self(String href, String title){
                    this.href = href;
                    this.title = title;
                }
            }

            public static final class Next {
                public final String href;
                public final String title;

                public Next(String href, String title){
                    this.href = href;
                    this.title = title;
                }
            }
        }
    }
}