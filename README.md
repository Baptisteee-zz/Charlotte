# Charlotte
Charlotte is a librarie made in java in order to make your life easier when you're coding discord bot using JDA with a plugin system
<hr>

# Summary
* [Contributors](#contributors)
* [Install Charlotte](#install-charlotte)
* [Use Charlotte](#use-charlotte)

# <a name="contributors"></a>Contributors
* Baptiste

# <a name="install-charlotte"></a>Install Charlotte
If you are not using maven or gradle, you can download the jar file [here](https://github.com/Baptisteee/Charlotte/releases/download/0.1/Charlotte-1.0-SNAPSHOT.jar)

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

## <a name="gradle"></a>Gradle
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

# <a name="use-charlotte"></a> Use Charlotte
In order to use charlotte, you have to download the jar [here](https://github.com/Baptisteee/Charlotte/releases/download/0.1/Charlotte-1.0-SNAPSHOT.jar) and to launch it in a folder. 
`java -jar Charlotte-1.0-SNAPSHOT.jar`
It will create a plugins folder and a config.yml file. In this file, you'll be able to change the prefix and to set your application token. When you will try to create a plugins using the dependency above, you will need to put a plugin.info file in your ressource folder. This file need to be like this : 
```
fr.yazhog.plugin.Main # You put your main class here
MyPlugin # And here you put your plugins' name 
```
