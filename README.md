# paronline

Paronline es un proyecto que utiliza la arquitectura rest, representa una tienda online, donde las tecnologías son las relevantes en el proyecto, implementa tecnologías y estructura descritos en los requerimientos.

# DB

```
Para la base de datos se utilizó un contendor docker de postges y para gestionarlo un cliente pgadmin que tambíen corre en un contenedor, en cualquier caso se puede utilizar una base de datos de preferencia, solo se debe modificar el modulo de conección y las clases que hacen referencia a la base de datos utilizada, el proyecto no está ligado a un gestor en particular
```
## Recursos opcionales 

docker pull postgres

docker pull dpage/pgadmin4

para configurar la conexión con pgadmin se debe utilizar la guía que se tiene en los sigueintes links del autor 

### POSTGRES 
https://hub.docker.com/_/postgres
### PGADMIN 
https://hub.docker.com/r/dpage/pgadmin4/


Se puede ejecutar el siguiente comando para verificar la ip y puerto correcto:

docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' some-postgresdocker inspect -f 'IP: {{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}, Puerto: {{range $p, $conf := .NetworkSettings.Ports}}{{$p}} {{end}}' nombre_del_contenedor
