
This API will calculate the cost of delivery of an item based on
* Weight (kg)
* Height (cm)
* Width (cm)
* Length (cm)

volume = height * width * length 

For calculating the cost of delivery, below rules should be considered and in order of priority.

| Priority      | Rule          | Condition                | Cost           |
|---------------|---------------|--------------------------|----------------|
| 1 (highest)   | Reject        | Weight exceeds 80 kg     | N/A            |
| 2             | Heavy item    | Weight exceeds 15 kg     | 30.00 * Weight |
| 3             | Small item    | Volume less than 2000 cm | 0.04 * Volume  |
| 4             | Medium item   | Volume less than 3500 cm | 0.06 * Volumne |
| 5             | Large item    |                          | 0.08 * Volume  |


Additional feature:

API should also accept a voucher code that can be used to provide a discount to the
customer. Retrieve the discount details by sending a request call to Voucher API using 
the voucher code (create a mock api/server)