# de-ds-docusign-template-library

Microservice designed to maintain a cache of DocuSign templates available in DS environment and inventory items 
available in inventory management system in order to serve library lookup requests from SalesForce with most 
appropriate enrollment agreement and fact sheet documents.
    
    +----------+
    | DocuSign | ------API------+     +--------+---------+
    +----------+                |---> |        |         |           +------------+
                                      |  CACHE | RULES   | <--API--> | SALESFORCE |
    +----------+                |---> |        |         |           +------------+
    |   IMS    | ------API------+     +--------+---------+
    +----------+                              
    
Example API Calls

    GET /templates/sku?sku=42700
    
    +--------+                +------+                     +------------------+
    | CLIENT |--[sku=42700]-->| API  |---[sku=42700]-----> | SkuLookupService |
    +--------+                |      |<--[NEW_YORK_CITY]-- |                  |
                              |      |<--[IMMERSIVE]-------|~~~~~~~~~~~~~~~~~~|     +-----+
                              |      |                     |    CACHE         |---->| IMS |
                              |      |                     +------------------+<----|     |
                              |      |                                              +-----+
                              |      |
                              |      |
                              |      |                                            +------------------------+
                              |      |----[NEW_YORK_CITY,IMMERSIVE,STANDARD*]---->| TemplateLibraryService |
                              |      |     * - default value                      |~~~~~~~~~~~~~~~~~~~~~~~~|
                              |      |<--[ {/* no templates matched by rules*/}]--|     DROOLS             |
                              +------+                                            +------------------------+
 
 
 
 
 



E.g. response:



    {
	    "request": {
    	"metro": "NEW_YORK_CITY",
		"paymentOption": "STANDARD",
		"programFormat": "IMMERSIVE",
		"requestedAt": "2018-04-25T12:35:42.981"
	    },
	    "response": {
    		"templateReferenceList": []
	    },
	    "inquiryId": "311dc387-715b-4290-913c-1443d921e20b"
    } 