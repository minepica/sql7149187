var loopback = require('loopback');

var app = require(path.resolve(__dirname, '../'));
var ds = app.datasources.mySQL; 

// Discover and build models from INVENTORY table
ds.discoverAndBuildModels('TEST1', {visited: {}, associations: true},
function (err, models) {
  // Now we have a list of models keyed by the model name
  // Find the first record from the inventory
  models.Inventory.findOne({}, function (err, inv) {
    if(err) {
      console.error(err);
      return;
    }
    console.log("\nInventory: ", inv);
    // Navigate to the product model
    inv.product(function (err, prod) {
      console.log("\nProduct: ", prod);
      console.log("\n ------------- ");
    });
  });
});