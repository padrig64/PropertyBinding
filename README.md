[![Latest Release](https://img.shields.io/github/release/LeanFrameworks/PropertiesFramework.svg)](https://github.com/LeanFrameworks/PropertiesFramework/releases/latest)
[![License](https://img.shields.io/badge/license-2--clause%20BSD-blue.svg)](https://raw.githubusercontent.com/LeanFrameworks/PropertiesFramework/master/LICENSE.md)
[![Build Status](https://travis-ci.org/LeanFrameworks/PropertiesFramework.svg?branch=master)](https://travis-ci.org/LeanFrameworks/PropertiesFramework)
[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=com.github.leanframeworks:propertiesframework-parent&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.github.leanframeworks:propertiesframework-parent)


# PropertiesFramework

The PropertiesFramework is a simple, flexible and extensible Java component that is:
* A library providing properties, property binding and property injection capabilities;
* A framework to extend it and build your own properties.

It is not meant to duplicate all JavaFX properties and their features, but rather to bring a very simple, lightweight,
yet flexible, similar concept to non-JavaFX application. So it can be used to bring properties and bindings, for
example, to Swing!

This project is extracted from the [ValidationFramework](https://github.com/padrig64/ValidationFramework), which will
later be migrated to use this project as its foundation. 

# Roadmap

The main changes expected in the upcoming releases are the following:
* Version 1.1.0: Add property injection mechanism to help implement a more composable, scalable MVC/MVP pattern
* Version 2.0.0: Harmonize Dispose and DeepDispose interfaces


# Project access

* Wiki: https://github.com/leanframeworks/PropertiesFramework/wiki
* Source code: https://github.com/leanframeworks/PropertiesFramework/
* Snapshot artifacts in Sonatype OSS Maven Repository: https://oss.sonatype.org/content/groups/public/com/github/leanframeworks/
* Released artifacts in Maven Central: http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.github.leanframeworks%22

# License

The PropertiesFramework is provided under the [Simplified BSD License (2-clause)](https://raw.githubusercontent.com/leanframeworks/PropertiesFramework/master/LICENSE.md).
