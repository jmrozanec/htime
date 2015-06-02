htime
===========
A Java library to make it easy for humans format a date. You no longer need to remember date time formatting chars: just write an example, and you will get the appropiate formatter. 

The project follows the [Semantic Versioning Convention](http://semver.org/) and uses Apache 2.0 license.

[![Flattr this!](https://api.flattr.com/button/flattr-badge-large.png)](https://flattr.com/submit/auto?user_id=jmrozanec&url=https://github.com/jmrozanec/htime)
[![Build Status](https://travis-ci.org/jmrozanec/htime.png?branch=master)](https://travis-ci.org/jmrozanec/htime)
[![Coverage Status](https://coveralls.io/repos/jmrozanec/htime/badge.png)](https://coveralls.io/r/jmrozanec/htime)

[![Project stats by OpenHub](https://www.openhub.net/p/htime/widgets/project_thin_badge.gif)](https://www.openhub.net/p/htime/)

**Download**

htime is available on [Maven central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.cronutils%22) repository.

    <dependency>
        <groupId>com.cronutils</groupId>
        <artifactId>htime</artifactId>
        <version>1.0.0</version>
    </dependency>


**Features**

 * Provide any date time format written in human readable text, and get the appropiate date time formatter!

**Usage Examples**

***Get JodaTime DateTimeFormatter***

    //define your own expressions to be formatted
    HDateTimeFormat hDateTimeFormat = HDateTimeFormatBuilder.getInstance().forJodaTime().getFormatter();;
    DateTimeFormatter jodaTimeFormatter = hDateTimeFormat.forPattern("June 9, 2011");
    String formattedDate = jodaTimeFormatter.print(DateTime.now());
    //formattedDate will be ex.: "June 9, 2015"


**Contribute & Support!**

Contributions are welcome! You can contribute by
 * star and/or Flattr this repo!
 * requesting or adding new features. Check our [roadmap](https://github.com/jmrozanec/htime/wiki/Roadmap)!
 * enhancing existing code: ex.: provide more accurate description cases
 * testing
 * enhancing documentation
 * providing translations to support new locales
 * bringing suggestions and reporting bugs
 * spreading the word / telling us how you use it!


Check [our page](http://cronutils.com)! For stats about the project, you can visit our [OpenHUB profile](https://www.openhub.net/p/htime).

Support us donating once or by subscription through Flattr!

[![Flattr this!](https://api.flattr.com/button/flattr-badge-large.png)](https://flattr.com/submit/auto?user_id=jmrozanec&url=https://github.com/jmrozanec/htime)
