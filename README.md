## Alfred

Suite de aplicativos para Java/Groovy. Esse projeto foi inicialmente criado por Marlon Silva Carvalho e hospedado no Google Code. Mantenho aqui um fork com várias outros funcionalidades.

## Uso

Como utilizamos o github para hospedar, a biblioteca JitPack tem nos ajudado muito para empacotar e distribuir o Alfred. Portanto para utilizá-lo em seu projeto basta adicionar um repositório e referenciar a lib da seguinte forma.


#### Maven 

```xml
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```

```xml
	<dependency>
	    <groupId>com.github.thiagonego</groupId>
	    <artifactId>alfred</artifactId>
	    <version>1.3.2</version>
	</dependency>
```

#### Gradle 

```grovy
	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```

```groovy
	compile group: 'com.github.thiagonego', name: 'alfred', version: '1.3.2'
```

#### Sbt 

```skala
	resolvers += "jitpack" at "https://jitpack.io"
```

```skala
	libraryDependencies += "com.github.thiagonego" % "alfred" % "1.3.2"	
```
