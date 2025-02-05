# Coupa Integration

## Dependencies

This project has the following main dependencies:

- JDK v11
- S1 parent POM v1.2.5 (com.sovos.platform:s1-spring-boot-parent-pom:1.2.5)
- Spring Boot v2.1.4 (org.springframework.boot:spring-boot-starter-parent:2.1.4.RELEASE)
- sovos-platform-core v2.4 (com.sovos.platform:sovos-platform-core:jar:2.4)
- gtd-rest-library v0.0.002 (com.sovos.integrations:gtd-rest-library:jar:0.0.002)

### Additional notes

- This project uses [com.spotify:docker-maven-plugin](https://github.com/spotify/docker-maven-plugin) for building a docker image.

## How to build and run

NOTE: To be able to run this service you will need some credentials in the application.properties file. Ask the Integrations team for a valid properties file before to continue.

1. Open the terminal and clone the repository inside your standard location of sovos projects:

```sh
cd C:\repos
git clone ssh://git@bitbucket.dev.sovos.local:7999/ab/coupa_integration.git
cd coupa_integration/
```

2. Copy 'src/main/resources/application.properties.template' to 'src/main/resources/application.properties' and update it as explained in the section bellow.

3. Make sure you are pointing to the correct target version of Java (check it by running `java -version` first). The following options shows examples of how to do it on different terminals.

- On git-bash shell:

```sh
export JAVA_HOME="C:\\Program Files\\Java\\jdk-11.0.4"
export PATH="$JAVA_HOME\\bin":$PATH
```

- On Windows Powershell:

```powershell
$env:JAVA_HOME = "C:\Program Files\Java\jdk-11.0.4"
$env:Path = "$env:JAVA_HOME\bin;$env:Path"
```

4. Build and run the project:

- Using the Spring Boot plugin:

```sh
mvn clean spring-boot:run
```

- Packaging and running the .jar file manually:

```sh
mvn clean package; java -jar target/coupa-integration-1.0.0.jar
```

## How to check if the API is working

Note, for the following curl commands, if you want to see more information like request and response headers then add the option '--verbose '.

- On local instance:

```
Request 1
===
Method: POST
Base URL: http://localhost:8083/api/coupa/v1
Resource path: /transactions/calculate
Headers:
  (required) Authorization=Bearer <jwt-token>
  (required) Content-Type=application/xml
  (required) Accept-Charset=application/xml

Example command:
curl \
--location --insecure --silent --show-error \
--request POST \
'http://localhost:8083/api/coupa/v1/transactions/calculate' \
--header 'Content-Type: application/xml' \
--header 'Accept-Charset: application/xml' \
--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJub25lIn0.eyJzdWIiOiIzMGI5NjZmMS0wN2QzLTRkNWItOWQ0Mi0wNGEwNWYzM2VhZWEiLCJvcmciOiIxYzhkNTY3Mi1mZTFhLTRlMTEtYjU2MC1kMWU1ZWQzYWI4MjAiLCJzY29wZSI6WyJTMV9SX09SRyIsIkNPVVBBX0NfVFJOUyJdLCJpc3MiOiJzb3ZvcyIsImFwaSI6dHJ1ZSwiZXhwIjoxNjMxOTA5MjA2LCJpYXQiOjE2MzE5MDU2MDYsImp0aSI6IjYyYTY4YmVmLTU4MmUtNGE2My1hOTQ4LTZkMDk3OWVhZWNiNCJ9.' \
--data-raw '<invoice-header><id type="integer">123</id><created-at type="dateTime">2021-03-04T05:31:44-08:00</created-at><updated-at type="dateTime">2021-03-04T05:37:13-08:00</updated-at><invoice-date type="dateTime">2021-03-04T00:00:00-08:00</invoice-date><delivery-date type="dateTime">2021-03-04T05:31:43-08:00</delivery-date><invoice-number>2345325</invoice-number><line-level-taxation type="boolean">false</line-level-taxation><status>draft</status><net-due-date type="dateTime">2021-04-18T23:59:59-07:00</net-due-date><discount-amount nil="true"/><paid type="boolean">false</paid><total-with-taxes type="decimal">1487.50</total-with-taxes><gross-total type="decimal">1487.50</gross-total><supplier-created type="boolean">false</supplier-created><document-type>Invoice</document-type><account-type><id type="integer">40</id><name>1 MASTER B3</name></account-type><currency><code>USD</code></currency><remit-to-address nil="true"/><ship-to-address><id type="integer">10391</id><name>Sovos Office</name><location-code nil="true"/><street1>200 Ballardvale St</street1><street2/><city>Wilmington</city><state>MA</state><postal-code>01887</postal-code><attention/><active type="boolean">true</active><business-group-name nil="true"/><vat-number nil="true"/><local-tax-number nil="true"/><country><code>US</code></country><vat-country nil="true"/></ship-to-address><bill-to-address><id type="integer">9627</id><name nil="true"/><location-code nil="true"/><street1>Bosques de Ciruelos 500</street1><street2>Bosques de Las Lomas</street2><city>CDMX</city><state>CDMX</state><postal-code>12500</postal-code><attention nil="true"/><active type="boolean">true</active><business-group-name nil="true"/><vat-number nil="true"/><local-tax-number nil="true"/><country><code>MX</code></country><vat-country nil="true"/></bill-to-address><supplier><id type="integer">8</id><name>CDW (USA)</name><display-name>CDW (USA)</display-name><number>649812-0</number></supplier><invoice-lines type="array"><invoice-line><id type="integer">78762</id><created-at type="dateTime">2021-03-04T05:31:44-08:00</created-at><updated-at type="dateTime">2021-03-04T05:37:13-08:00</updated-at><accounting-total type="decimal">0.00</accounting-total><description>MacBook Pro Retina - 15-inch</description><line-num type="integer">1</line-num><order-header-num type="integer">24565</order-header-num><po-number>DN24565</po-number><order-line-id type="integer">57685</order-line-id><order-line-num>1</order-line-num><price type="decimal">55421.00</price><net-weight nil="true"/><price-per-uom type="decimal">0.00</price-per-uom><quantity type="decimal">1.0</quantity><status>new</status><total type="decimal">1400.00</total><type>InvoiceQuantityLine</type><discount-amount nil="true"/><company-uom nil="true"/><property-tax-account nil="true"/><source-part-num>MGXA2LL/A</source-part-num><order-line-source-part-num>MGXA2LL/A</order-line-source-part-num><category>goods</category><subcategory nil="true"/><deductibility nil="true"/><account><id type="integer">1563</id><name>San Francisco - Marketing, Assets</name><code>SF-Marketing-Assets</code></account><account-allocations type="array"/><accounting-total-currency><id type="integer">1</id><code>USD</code><decimals type="integer">2</decimals></accounting-total-currency><currency><id type="integer">1</id><code>USD</code><decimals type="integer">2</decimals></currency><item><id type="integer">2759</id><item-number>MGXA2LL/A</item-number><name>MacBook Pro Retina - 15-inch</name></item><uom><code>EA</code></uom><weight-uom nil="true"/><order-line-commodity><name>Laptops and Tablets</name><deductibility nil="true"/><category nil="true"/><subcategory nil="true"/></order-line-commodity><commodity><name>Laptops and Tablets</name><deductibility nil="true"/><category nil="true"/><subcategory nil="true"/></commodity><created-by><id type="integer">688</id><login>lrodriguez</login><employee-number/></created-by><updated-by><id type="integer">688</id><login>lrodriguez</login><employee-number/></updated-by><custom-fields><tax-jurisdictions- nil="true"/><custom-field-7/><taxware-tax-amount nil="true"/><accrual-amount nil="true"/><taxware-tax-rates nil="true"/></custom-fields></invoice-line></invoice-lines><approvals type="array"/><requested-by><id type="integer">688</id><login>lrodriguez</login><employee-number/></requested-by><created-by><id type="integer">688</id><login>lrodriguez</login><employee-number/></created-by><updated-by><id type="integer">688</id><login>lrodriguez</login><employee-number/></updated-by><custom-fields><bill-only type="boolean">false</bill-only><spendguard-evaluator type="boolean">false</spendguard-evaluator></custom-fields></invoice-header>'
```


### How to configure the client and organization on S1 INT

NOTE: do not confuse S1 organizations with organizations in GTD (aka GTD orgcode). S1 organizations are those orgs and child orgs that you configure in the S1 portal, while GTD orgs are configured in the UI of GTD itself.

1. Go to https://integration-s1.sovos.org/settings/clients/manage-clients, search and select the client and, in the Products tab, activate the Coupa product.

2. Go to https://integration-s1.sovos.org/settings/orgs/manage-orgs, search the organization with the same name as the client, click it or a child organization inside.

3. Configure the values of Coupa for this organization. (Ask Integrations team for default values they use for testing). Also activate all the necessary permissions (COUPA_C_TRNS and S1_R_ORG), because there are not activated permissions by default.

###  How to extract the S1 Organization ID from the S1 INT portal

You can extract this value by opening the developer tools console in your browser while you have an organization configuration panel opened inside the S1 UI (you open this panel by clicking the target organization inside the https://integration-s1.sovos.org/settings/orgs/manage-orgs page). Inside the developer tools console, go to the Session Storage table and copy the value of the s1Context key.

### Generating a valid jwt token for testing on local instance

The following is the minimal metadata required to generate a valid JWT for requests to both this integration and the "/api/v1/iam/orgs/{orgId}" resource of the IAM service:

```
Header
{
  "typ": "JWT",
  "alg": "HS256"
}

Payload
{
  "org": "{orgId}",
  "scope": [
    "S1_R_ORG",
    "COUPA_C_TRNS"
  ]
}
```

Here you can see we are generating a token with all the scopes (permissions) required to access all the resources in the integration. S1_R_ORG is also necessary if we want to call the "/api/v1/iam/orgs/{orgId}" resource of the IAM service.

Note that one required JWT claim in addition to "scopes" is "org", here is were you have to put the corresponding S1 Organization ID extracted from the S1 portal.

Go to [https://jwt.io/](https://jwt.io/) and copy the encoded jwt token that is generated when using this metadata. Don't forget to replace {orgId} with the correct value.

## Project configuration properties

TODO