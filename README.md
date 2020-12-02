# Charlotte
Charlotte is a librarie made in java in order to make your life easier when you're coding discord bot using JDA
<hr>

# Summary
* [Contributors](#contributors)
* [Install Charlotte](#install-charlotte)

# <a name="contributors"></a>Contributors
* Baptiste

# <a name="install-charlotte"></a>Install Charlotte
If you are not using maven or gradle, you can use the jar file [here](https://github.com/Baptisteee/Charlotte/releases/download/0.1/Charlotte-1.0-SNAPSHOT.jar)

## <a name="maven"></a>Maven
```
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>

	<dependency>
	    <groupId>com.github.Baptisteee</groupId>
	    <artifactId>Charlotte</artifactId>
	    <version>0.1</version>
	</dependency>
```

## <a name="Gradles"></a>Gradle
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

	dependencies {
	        implementation 'com.github.Baptisteee:Charlotte:0.1'
	}
```
