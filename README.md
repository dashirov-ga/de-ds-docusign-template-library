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
    
