

# product-visibility-search

  

## _Enfoque funcional y técnico_

  

### Funcional

  

Como entregable de esta prueba técnica, se han implementado las siguientes funcionalidades:

 - Algoritmo de filtrado de productos en base a las especificaciones propuestas.
 - Una vez que la aplicación arranca, se cargan los datos de 3 ficheros CSV contenidos en el proyecto. Estos ficheros contienen los datos especificados en la prueba.
 - Una vez cargados los datos de los ficheros, se persisten en una base de datos en memoria (H2) con el objeto de poder posteriormente cargar esta información para testear el algoritmo de visibilidad.

Desde un punto de vista técnico, se ha utilizado Java 17, Spring Boot 3, Flyway, H2 y Maven. H2 no es una base de datos para un entorno real, pero al ser una base de datos simple en memoria, resulta útil para este tipo de desarrollos.
  

### Técnico

  

##### _Modelo de datos_

Aunque el ejercicio presenta 3 archivos para los datos de product, size y stock, como entidades (clases que se mapean en tablas de la BBDD) se identifican Product y Size, mientras que la clase Stock es un Embeddable de la clase Size. Esto es, la clase Stock no tiene identidad propia y su información se mapea directamente sobre la tabla  size.
Se ha usado JPA como tecnología de persistencia y mapeo de los objetos a la BBDD.

*Clase Product*
La clase Product contiene los campos detallados en el documento (id, sequence), así como una lista de *Sizes*. Es importante contener el conjunto de sizes del producto para poder desarrollar el algoritmo de filtrado principal. Se añaden métodos de utilidad (addSize y removeSize) para facilitar la gestión de las tallas del producto.
Como para cada producto, no debería haber muchas tallas, para facilitar la legibilidad del código, se ha optado por realizar un Fetch.EAGER de las tallas en el producto. En caso real (producción), conociendo mejor las posibles casuísticas, se podría cambiar a un Fetch.LAZY y optimizar las consultas necesarias a la base de datos cuando se quisiera obtener vía JPA los datos de un producto.
También es destacable mencionar en este objeto un par de Sets que no se mapean a la base de datos, pero que son importantes para el algoritmo de visibilidad, permitiendo distinguir las tallas de un producto que son especiales de las que no lo son (se han denominado *ordinarias*).
Esta clase dispone también de un método *isSearchable* que aglutina la lógica necesaria para determinar si un producto se puede mostrar en los frontales web (lógica principal requerida).


*Clase Size*
Sirve para mapear en su tabla correspondiente (size), los datos de su id, producto al que pertenece, si es backsoon y si es especial, así como su Stock (Stock se gestiona en un objeto aparte).
Por las características de esta clase y sus propiedades, se ha creado un Builder para facilitar la construcción de este objeto por parte de una aplicación cliente (en este caso han sido los propios tests del desarrollo).
A destacar de este objeto los métodos *hasStock* y *isSearchable* que son usados también en la lógica principal de la aplicación de filtrado de productos para la web.


*Clase Stock*
Por último, la clase Stock representa el stock disponible de una talla (quantity) y como se ha mencionado anteriormente, se ha gestionado como un componente de la clase Size.


*Ciclo de vida de la BBDD (estructura)*
Para gestionar el ciclo de vida de la estructura de la base de datos se ha optado por Flyway, un framework simple de migración de BBDD o dicho de otro modo, cómo gestionar la estructura de una base de datos relacional gestionando su versionado.
Para este desarrollo, existen solo dos ficheros: uno para definir la tabla de product y otro para la tabla size. Se podría haber hecho con un único fichero, pero por ciclo de iteración en el desarrollo, se crearon dos ficheros.

##### _Lectura de ficheros CSV_
Como adaptadores, se ha creado una interfaz *CSVDataLoader* implementada por *CSVDataLoaderImpl* que permite leer el contenido de los ficheros CSV almacenados en el proyecto como recursos (en *src/main/resources/csv*).
Esta implementación delega parte de la lógica en el proyecto *commons-csv* de Apache.
Se ha intentado a su vez, minimizar la repetición de código para leer los ficheros con ayuda del método privado *loadDataRecord* de CSVDataLoaderImpl.
También se han creado Java records (ProductRecord, SizeRecord y StockRecord) que se utilizan para leer la información de cada línea de los ficheros CSV correspondientes.

##### *Carga de datos*

Se ha creado una interfaz *Data Loader*, cuya implementación (*DataLoaderImpl*) se encarga de leer el contenido de los ficheros CSV a partir de la utilidad comentada anteriormente (CSVDataLoader) y a través de repositorios de Spring Data JPA para Product y Size (*ProductRepository y SizeRepository*), persistir en la base de datos los datos de Product y Size, junto con Stock, obtenidos de los ficheros.

Se  han  considerado  varias  opciones. Desde  un  enfoque  más  dinámico, de  forma  que el input fuera  un JSON codificado  en Base64 para  ser  incluido  como _query parameter_ a uno  más  estático  que  permita  modificar  la  implementación  de  forma  más  sencilla sin impactar a los  clientes  del API. Esta  última  estrategia ha sido  la  adoptada.


##### *Estructuras de datos*
En la implementación de esta prueba, se han usado diferentes clases de estructuras de datos en los objetos de dominio.
En general, suelo utilizar List como objetos de colección por tener una pequeña ventaja en rendimiento de operaciones (en condiciones generales) que los Set, aunque los Set son conveniente usarlos cuando por la lógica del algoritmo no se desea que haya objetos repetidos contenidos en la colección.
De esta forma, se ha seleccionado:

 - Clase Product: una lista de objetos Size para mantener la relación OneToMany con esta propiedad (objeto). Internamente se usan también dos Sets de tallas para distinguir las "normales" de las "especiales" dentro de un mismo producto. Estos Sets son utilizados en el cálculo del algoritmo de visibilidad.

El resto de objetos del dominio principal no usa colecciones de objetos.

##### *Lógica principal*
Por último, para resolver el algoritmo principal de filtrado de productos para la web, se ha definido la interfaz *ProductService* con dos métodos sobrecargados:

 1. productSearch sin argumentos: llama al segundo método pasándole todos los productos existentes en la base de datos. Sirve esencialmente para probar el resultado esperado.
 2. productSearch con argumento de la lista de productos que se desea filtrar: con la lista de productos del argumento, ejecuta la lógica de filtrado para obtener los productos que cumplen los requisitos solicitados.

Con ayuda de los métodos ya existentes en los objetos principales de dominio Product y Size, la implementación de la lógica principal es bastante sencilla a partir del uso de *streams* de colecciones de Java.

##### *Complejidad temporal del algoritmo*- Posible mejora
Honestamente, a lo largo de mi carrera nunca he tenido que estimar la complejidad temporal de un algoritmo o desarrollo en notación O, por lo que resulta complicado realizar una estimación de su eficiencia.
En resumen, el algoritmo recorre los productos solicitados para determinar los que son visibles y esta lógica la realiza recorriendo sus tallas y determinando si el producto es visible o no en función de los requisitos establecidos.
En cualquier caso, en este desarrollo se ha querido primar la facilidad de entendimiento (código limpio) sobre la eficiencia.
Como mejora, *y de hecho sería la aplicabilidad de este algoritmo en un entorno real*, el desarrollo se ha realizado en base a la propuesta de los datos suministrados (datos estáticos). En un entorno real, los datos de stock y backsoon en las tallas estarían cambiando constantemente y posiblemente el algoritmo en este tipo de entorno debería modificarse para capturar estos cambios de datos al instante mediante eventos y un suscriptor de estos eventos sería un algoritmo similar al desarrollado en este ejercicio que lleve ya el resultado de si un producto es visible o no a un modelo de persistencia (BBDD), de forma que a la hora de consultar que productos son visibles o no, no hay que realizar ningún cálculo en ese momento, sino simplemente interpretar los datos previamente calculados y almacenados por el algoritmo en la gestión del evento. Esa sería una mejora clara del algoritmo de forma que fuera más reutilizable en un entorno productivo con los datos de stock y backsoon en constante modificación.

##### *API Rest*

El ejercicio incluye una interfaz HTTP en forma de API REST a partir de la URL /visibleProducts.
**NOTA**: este API dista mucho en su definición de cómo sería en un desarrollo real. En un API real, posiblemente el endpoint sería /products junto con uno o varios parámetros que pudieran definir que se quiere solo obtener los productos visibles. Por otro lado, ni siquiera el API devuelve un típico objeto JSON, sino que devuelve la respuesta en texto plano, tal y como está especificado en el ejercicio. Solo se ha incluido este endpoint como una forma manual (vía HTTP) para verificar que la lógica de negocio devuelve el String con los ids de productos visibles en el orden esperado.


##### *Tests*

El código incluye algunos tests unitarios y de integración para probar los aspectos esenciales de la implementación.
También incluye un test unitario del controlador (RestController) del API.

  

## Ejecución

 1. A través de Maven y Java

Se aconseja usar el wrapper de Maven. Para ello hay que tener previamente instalado Maven y sobre el directorio principal del proyecto (donde reside el archivo pom.xml), ejecutar

    mvn -N wrapper:wrapper

Esto habilitará el wrapper de Maven para las siguientes ejecuciones.


El proyecto  requiere Java 17 para  su  correcto  funcionamiento. Al  ser  un  proyecto  de Spring Boot, sobre el directorio principal del  proyecto (directorio product-visibility-search que  contiene  el archivo pom.xml) ejecutar

    mvnw clean install

Esto borrará instalaciones previas, en la primera ejecución descargará las dependencias necesarias, ejecutará los tests y finalmente construirá el fichero .jar del proyecto.

Una vez finalizada la ejecución, a continuación basta con ejecutar el jar generado anteriormente.

    java -jar target/product-visibility-search-0.0.1-SNAPSHOT.jar

 2. A través de Docker

Otra  opción  es  tener Docker instalado y el demonio de Docker en ejecución en el equipo. En  este  caso, desde el directorio principal del proyecto, basta  con  escribir

    mvnw spring-boot:build-image

La primera ejecución puede tardar bastante, ya que tiene que descargar todas las layers correspondientes.
Una vez terminado, se  puede  comprobar  que  en  las  imágenes  de Docker existe  una  que  es inditex/product-search-visibility ejecutando

    docker images

Y en  ese  caso, se  puede  ejecutar  la  aplicación  con

    docker run -p 8080:8080 inditex/product-search-visibility
