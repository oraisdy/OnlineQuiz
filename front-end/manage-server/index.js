"use strict";
const manage = require("./manage");
const PORT = process.env.PORT || 3000;
manage.listen(PORT, () => {
    console.log(`manage listening on port ${PORT}!`);
});
