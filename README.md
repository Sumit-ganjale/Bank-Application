# Bank-Application
Bank Application

Application provides Apis for Create,Update,Delete,Find customer and Account details information
also Api's for fund transfer,deposit,withdraw services

{
  "openapi" : "3.0.0",
  "info" : {
    "title" : "Banking Application API",
    "description" : " API has operations to persist, update and retrieve various customer and account details
    "version" : "v1"
  },
  "url":"http://localhost:8080/Bank/"
  "paths" : {
    "/addCustomer" : {
      "POST" : {
        "tags" : [ "Customer" ],
        "summary" : " Accounts",
        "description" : "This operation will create customer and Account based on inputs provided.",
        "operationId" : "addCustomer",
        "requestBody" : {
          "description" : "Request to create agency.",
          "content" : {
            "application/json" : {
              "schema" : {
                "$ref" : "#/components/schemas/Customer"
              }
            }
          }
        }
		
		"responses" : {
          "200" : {
            "description" : "Successful operation",
            "content" : {
              "application/json" : {
                "Account Created Successfully"
                }
              }
            }
          },
          "400" : {
            "description" : "Bad Request",
            "content" : {
              "application/json" : {
                "schema" : {
                  "Error Message"
                }
              }
            }
          },
          "404" : {
            "description" : "Resource not found",
            "content" : {
              "application/json" : {
                "schema" : {
                  "Error Massage"
                }
              }
            }
          },
          "500" : {
            "description" : "Server not available.",
            "content" : {
              "application/json" : {
                "schema" : {
                  "ErrorMessage"
                }
              }
            }
          }
        }
      },
    "/getCustomer" : {
      "get" : {
        "tags" : [ "Customer" ],
        "summary" : "Retrieve Customer",
        "description" : "This operation retrieves Customer  details from the billing.",
        "operationId" : "retrieveAccountDetails",
        "responses" : {
          "200" : {
            "description" : "Successful operation.",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/Customers"
                }
              }
            }
          },

          "404" : {
            "description" : "Resource not found",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/Error"
                }
              }
            }
          },
          "500" : {
            "description" : "Server not available.",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/Error"
                }
              }
            }
          }
        }
      }
	 }
    "/update"{
	 "put" : {
        "tags" : [ "Accounts" ],
        "summary" : "Update Account",
        "description" : "This operation will update an existing account in billing system for a given account number.",
        "operationId" : "updateAccount",
        ,
        "requestBody" : {
          "description" : "Request to update account.",
          "content" : {
            "application/json" : {
              "schema" : {
                "$ref" : "#/components/schemas/Customer"
              }
            }
          }
        },
        "responses" : {
          "200" : {
            "description" : "Successful operation",
            "content" : {
              "application/json" : {
                "schema" : {
                  "Customer Updated Successfully"
                }
              }
            }
          },
          "400" : {
            "description" : "Bad Request",
            "content" : {
              "application/json" : {
                "schema" : {
                  "Error Message"
                }
              }
            }
          },
          "404" : {
            "description" : "Resource not found",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/Error"
                }
              }
            }
          },
          "500" : {
            "description" : "Server not available.",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/Error"
                }
              }
            }
          }
        }
      }}
    "/delete" : {
      "Delete" : {
        "tags" : [ "Customer" ],
        "summary" : "",
        "description" : "This operation will delete given customer",
        "operationId" : "deleteCustomer",
       "requestBody" : {
          "description" : "Request to create agency.",
          "content" : {
            "application/json" : {
              "schema" : {
                "$ref" : "#/components/schemas/AgencyDetail"
              }
            }
          }
        },
	   "responses" : {
          "200" : {
            "description" : "Successful operation",
            "content" : {
              "application/json" : {
                "schema" : {
                 "Customer Deleted Successfully"
                }
              }
            }
          },
          "400" : {
            "description" : "Bad Request",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/Error"
                }
              }
            }
          },
          "404" : {
            "description" : "Resource not found",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/Error"
                }
              }
            }
          },
          "500" : {
            "description" : "Server not available",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/Error"
                }
              }
            }
          }
        }
		}}
    "/fundTransfer/" : {
      "put" : {
        "parameters" : [ {
          "name" : "toAccount",
          "in" : "RequestParam",
          "description" : "Account from which amount transfered.",
          "required" : true,
          "style" : "simple",
          "explode" : false,
          "schema" : {
            "maxLength" : 20,
            "type" : "string",
            "example" : "AG123"
          }
        }, {
          "name" : "fromAccount",
          "in" : "RequestParam",
          "description" : "Account to which amount transfered",
          "required" : true,
          "style" : "form",
          "explode" : true,
          "schema" : {
            "maxLength" : 30,
            "type" : "string",
            "example" : "1820107"
          }
        }, {
          "name" : "amount",
          "in" : "RequestParam",
          "description" : "amount",
          "required" : false,
          "style" : "form",
          "explode" : true,
          "schema" : {
            "type" : "long",
            "format" : "100.00",
            "example" : "100.00"
          }
        }, "name" : "firstName",
          "in" : "path",
          "description" : "Billing Account Number.",
          "required" : true,
          "style" : "simple",
          "explode" : false,
          "schema" : {
            "maxLength" : 20,
            "type" : "string",
            "example" : "ACC123"
          }
        }, {
          "name" : "phoneNumber",
          "in" : "path",
          "description" : "Request sequence generated in the source system to uniquely identify a request.",
          "required" : true,
          "style" : "form",
          "explode" : true,
          "schema" : {
            "maxLength" : 10,
            "type" : "long",
            "example" : "1820107"
          }],
        "responses" : {
          "200" : {
            "description" : "Successful operation.",
            "content" : {
              "application/json" : {
                "schema" : {
                  "Fund Transfer Successful"
                }
              }
            }
          },
          "400" : {
            "description" : "Bad Request",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/Error"
                }
              }
            }
          },
          "404" : {
            "description" : "Resource not found",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/Error"
                }
              }
            }
          },
          "500" : {
            "description" : "Server not available.",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/Error"
                }
              }
            }
          }
        }
      }
	  }
	"/{firstName}/{phoneNumber}" : {
       "get" : {
        "tags" : [ "Customer" ],
        "summary" : "Retrieve Entity Roles",
        "description" : "This operation retrieves Customer and account details information ",
        "operationId" : "findByID",
        "parameters" : [ {
          "name" : "firstName",
          "in" : "path",
          "description" : "Billing Account Number.",
          "required" : true,
          "style" : "simple",
          "explode" : false,
          "schema" : {
            "maxLength" : 20,
            "type" : "string",
            "example" : "ACC123"
          }
        }, {
          "name" : "phoneNumber",
          "in" : "path",
          "description" : "Request sequence generated in the source system to uniquely identify a request.",
          "required" : true,
          "style" : "form",
          "explode" : true,
          "schema" : {
            "maxLength" : 10,
            "type" : "integer",
            "example" : "1820107"
          }
        ]}
		{
        "responses" : {
          "200" : {
            "description" : "Successful operation.",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/Customer"
                }
              }
            }
          },
          "400" : {
            "description" : "Bad Request",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/Error"
                }
              }
            }
          },
          "404" : {
            "description" : "Resource not found",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/Error"
                }
              }
            }
          },
          "500" : {
            "description" : "Server not available.",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/Error"
                }
              }
            }
          }
      }}}},
	"/Account/{firstName}/{phoneNumber}" : {
       "get" : {
        "tags" : [ "Customer" ],
        "summary" : "Retrieve Entity Roles",
        "description" : "This operation retrieves Customer and account details information ",
        "operationId" : "getAccountDetails"
        "parameters" : [ {
          "name" : "firstName",
          "in" : "path",
          "description" : "Billing Account Number.",
          "required" : true,
          "style" : "simple",
          "explode" : false,
          "schema" : {
            "maxLength" : 20,
            "type" : "integer"
            "example" : "ACC123"
          }
        }, {
          "name" : "phoneNumber",
          "in" : "path",
          "description" : "Request sequence generated in the source system to uniquely identify a request.",
          "required" : true,
          "style" : "form",
          "explode" : true,
          "schema" : {
            "maxLength" : 10,
            "type" : "integer",
            "example" : "1820107"
          }
        ]}
		{
        "responses" : {
          "200" : {
            "description" : "Successful operation.",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/Customer"
                }
              }
            }
          },
          "400" : {
            "description" : "Bad Request",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/Error"
                }
              }
            }
          },
          "404" : {
            "description" : "Resource not found",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/Error"
                }
              }
            }
          },

          "500" : {
            "description" : "Server not available.",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/Error"
                }
              }
            }
          }
      } 
	  }
	}}
	"/withdraw" : {
      "put" : {
        "parameters" : [ {
          "name" : "fromAccount",
          "in" : "RequestParam",
          "description" : "Account to which amount transfered",
          "required" : true,
          "style" : "form",
          "explode" : true,
          "schema" : {
            "maxLength" : 30,
            "type" : "string",
            "example" : "1820107"
          }
        }, {
          "name" : "amount",
          "in" : "RequestParam",
          "description" : "amount",
          "required" : false,
          "style" : "form",
          "explode" : true,
          "schema" : {
            "type" : "long",
            "format" : "100.00",
            "example" : "100.00"
          }
        }, "name" : "firstName",
          "in" : "path",
          "description" : "Billing Account Number.",
          "required" : true,
          "style" : "simple",
          "explode" : false,
          "schema" : {
            "maxLength" : 20,
            "type" : "string",
            "example" : "ACC123"
          }
        }, {
          "name" : "phoneNumber",
          "in" : "path",
          "description" : "Request sequence generated in the source system to uniquely identify a request.",
          "required" : true,
          "style" : "form",
          "explode" : true,
          "schema" : {
            "maxLength" : 10,
            "type" : "long",
            "example" : "1820107"
          }],
        "responses" : {
          "200" : {
            "description" : "Successful operation.",
            "content" : {
              "application/json" : {
                "schema" : {
                  "amount Withdraw Successful"
                }
              }
            }
          },
          "400" : {
            "description" : "Bad Request",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/Errors"
                }
              }
            }
          },
          "404" : {
            "description" : "Resource not found",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/Error"
                }
              }
            }
          },
          "500" : {
            "description" : "Server not available.",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/Error"
                }
              }
            }
          }
        }
      }
	  }
	"/deposit" : {
      "put" : {
        "parameters" : [ {
          "name" : "toAccount",
          "in" : "RequestParam",
          "description" : "Account from which amount transfered.",
          "required" : true,
          "style" : "simple",
          "explode" : false,
          "schema" : {
            "maxLength" : 20,
            "type" : "string",
            "example" : "AG123"
          }
        }, {
          "name" : "amount",
          "in" : "RequestParam",
          "description" : "amount",
          "required" : false,
          "style" : "form",
          "explode" : true,
          "schema" : {
            "type" : "long",
            "format" : "100.00",
            "example" : "100.00"
          }
        }, "name" : "firstName",
          "in" : "path",
          "description" : "Billing Account Number.",
          "required" : true,
          "style" : "simple",
          "explode" : false,
          "schema" : {
            "maxLength" : 20,
            "type" : "string",
            "example" : "ACC123"
          }
        }, {
          "name" : "phoneNumber",
          "in" : "path",
          "description" : "Request sequence generated in the source system to uniquely identify a request.",
          "required" : true,
          "style" : "form",
          "explode" : true,
          "schema" : {
            "maxLength" : 10,
            "type" : "long",
            "example" : "1820107"
          }],
        "responses" : {
          "200" : {
            "description" : "Successful operation.",
            "content" : {
              "application/json" : {
                "schema" : {
                  "Fund Transfer Successful"
                }
              }
            }
          },
          "400" : {
            "description" : "Bad Request",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/Error"
                }
              }
            }
          },
          "404" : {
            "description" : "Resource not found",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/Error"
                }
              }
            }
          },
          "500" : {
            "description" : "Server not available.",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/Error"
                }
              }
            }
          }
        }
      }
	  }
	  
   "Components" {
	"Schemas":{

		"Customers":
			"required" : [ "firstName, lastName, address, phoneNumber" ],
			"type" : "object",
			"properties" : {
			"firstName" : {
            "type" : "string",
            "description" : "System/Application error code",
            "example" : "ERR-001"
			},
			"lastName" : {
            "type" : "string",
            "description" : "Verbose, plain language description of the problem for the app developer with hints about how to fix it.",
            "example" : "Message for app developer"
			},
			"address" : {
            "type" : "string",
            "description" : "Pass this message on to the app user if needed.",
            "example" : "Message for end user"
			},
			"phoneNumber" : {
            "type" : "string",
            "description" : "Pass this message on to the app user if needed.",
            "example" : "Message for end user"
			}
			"account":{
			"type":"Arrays"
			"$ref":"#/components/schemas/Account"
		  
		  },
		  
		"Account":{
			"required" : [ "firstName, lastName, address, phoneNumber" ],
			"type" : "object",
			"properties" : {
				"AccountType" : {
				"type" : "string",
				"description" : "System/Application error code",
				"example" : "ERR-001"
			},
				"AccountNumber" : {
				"type" : "long",
				"description" : "Verbose, plain language description of the problem for the app developer with hints about how to fix it.",
				"example" : "Message for app developer"
			},
				"Amount" : {
				"type" : "long",
				"description" : "Pass this message on to the app user if needed.",
				"example" : "Message for end user"
			}
		}
    "Errors" : {
        "required" : [ "Message" ],
        "type" : "object",
        "properties" : {
          "message" : {
            "type" : "string",
            "description" : "System/Application error code",
            "example" : "ERR-001"
          },
          "statuscode" : {
            "type" : "string",
            "description" : "Verbose, plain language description of the problem for the app developer with hints about how to fix it.",
            "example" : "Message for app developer"
          }
          
        }
      }
    }
  }
}
