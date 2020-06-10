/*jshint esversion: 6 */
const express = require('express');
const path = require('path');
const app = express();
app.use(express.static('dist/PFPedro'));
app.get('/*', function(req, res) {
    res.sendFile('dist/PFPedro/index.html', { root: __dirname });
});
app.listen(process.env.PORT || 8080);