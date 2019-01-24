const express = require("express");
const bodyParser = require('body-parser');
const app = express();

app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

let medicina = {
    nombre:'',
    costo: '',
    tabletas:''

};

let respuesta = {
    error: false,
    codigo: 200,
    mensaje: ''
};

app.get('/', function(req, res) {
    respuesta = {
        error: true,
        codigo: 200,
        mensaje: 'Punto de inicio'
    };
    res.send(respuesta);
});

app.route('/medicina')
    .get(function (req, res) {
        respuesta = {
            error: false,
            codigo: 200,
            mensaje: ''
        };
        if(medicina.nombre === '' || medicina.costo === '' || medicina.tabletas == '') {
            respuesta = {
                error: true,
                codigo: 501,
                mensaje: 'Medicina no ha sido creada'
            };
        } else {
            respuesta = {
                error: false,
                codigo: 200,
                mensaje: 'respuesta de la medicina',
                respuesta: medicina
            };
        }
        res.send(respuesta);
    })
    .post(function (req, res) {
        if(!req.body.nombre || !req.body.costo || !req.body.tabletas) {
            respuesta = {
                error: true,
                codigo: 502,
                mensaje: 'Los campos nombre, costo y tabletas son requeridos'
            };
        } else {
            if(medicina.nombre !== '' || medicina.costo!== '' || medicina.tabletas!== '') {
                respuesta = {
                    error: true,
                    codigo: 503,
                    mensaje: 'La medicina fue creada previamente'
                };
            } else {
                medicina = {
                    nombre: req.body.nombre,
                    costo: req.body.costo,
                    tabletas:req.body.tabletas
                };
                respuesta = {
                    error: false,
                    codigo: 200,
                    mensaje: 'Medicina creada',
                    respuesta: medicina
                };
            }
        }

        res.send(respuesta);
    })
    .put(function (req, res) {
        if(!req.body.nombre || !req.body.costo || !req.body.tabletas) {
            respuesta = {
                error: true,
                codigo: 502,
                mensaje: 'Los campos nombre, costo y tabletas son requeridos'
            };
        } else {
            if(medicina.nombre === '' || medicina.costo=== '' || medicina.tabletas=== '') {
                respuesta = {
                    error: true,
                    codigo: 501,
                    mensaje: 'La medicina no ha sido creada'
                };
            } else {
                medicina = {
                    nombre: req.body.nombre,
                    costo: req.body.costo,
                    tabletas:req.body.tabletas
                };
                respuesta = {
                    error: false,
                    codigo: 200,
                    mensaje: 'Medicina actualizada',
                    respuesta: medicina
                };
            }
        }

        res.send(respuesta);
    })
    .delete(function (req, res) {
        if(medicina.nombre === '' || medicina.costo === '' || medicina.tabletas === '') {
            respuesta = {
                error: true,
                codigo: 501,
                mensaje: 'La medicina no ha sido creada'
            };
        } else {
            respuesta = {
                error: false,
                codigo: 200,
                mensaje: 'Medicina eliminada'
            };
            medicina = {
                nombre: '',
                costo: '',
                tabletas:''
            };
        }
        res.send(respuesta);
    });

app.use(function(req, res, next) {
    respuesta = {
        error: true,
        codigo: 404,
        mensaje: 'URL no encontrada'
    };
    res.status(404).send(respuesta);
});

app.listen(3000, () => {
    console.log("El servidor está inicializado en el puerto 3000");
});