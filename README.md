<a href="https://codebeat.co/projects/github-com-heitorcolangelo-kappuccino-develop"><img alt="codebeat badge" src="https://codebeat.co/badges/944d0ef8-1e6c-422d-9e03-d6a83d342c9b" /></a>
# kappuccino
A kotlin library to simplify the way you do instrumentation tests with Espresso library.

Here is how you do instrumentation tests today:

``` kotlin
@Test fun loginFieldsAreVisible() {
  onView(withId(R.id.username)).check(matches(isDisplayed())
  onView(withId(R.id.password)).check(matches(isDisplayed())
  onView(withId(R.id.login_button)).check(matches(isDisplayed())
}
```
This is just to check a simple login screen, and we are not even considering that we may need to scroll to one of these views,
due to small screens support.
With scroll, our test will be something like that:

``` kotlin
@Test fun loginFieldsAreVisible() {
  onView(withId(R.id.username)).perform(scrollTo()).check(matches(isDisplayed())
  onView(withId(R.id.password)).perform(scrollTo()).check(matches(isDisplayed())
  onView(withId(R.id.login_button)).perform(scrollTo()).check(matches(isDisplayed())
}
```

We have to repeat a lot of code, this makes the tests hard to read and understand at a first look, and you may forget some scrollTo(), or mismatch the check function.
And at the end, all we want is to check if the views with theses ids are displayed.
So, this is how you do the same test with kappuccino library:

``` kotlin
@Test fun loginFieldsAreVisible() {
  displayed {
    id(R.id.username)
    id(R.id.password)
    id(R.id.login_button)
  }
}
```

Cleaner, easier to write and understand. 
To scroll, all you have to do is pass a parameter to the function:

``` kotlin
@Test fun loginFieldsAreVisible() {
  displayed(scroll = true) {
    id(R.id.username)
    id(R.id.password)
    id(R.id.login_button)
  }
}
```

## Installation

1 - Setup kotlin in your project, see the instructions here:
    <a href="https://kotlinlang.org/docs/tutorials/kotlin-android.html/>

2 - Create a kotlin directory into 'src/androidTest/'

3 - Set you sourceDataSet into your build.gradle file

``` groovy
sourceSets {
    androidTest.java.srcDirs = ['src/androidTest/kotlin']
  }
```

4 - Add library into your build.gradle file and sync

``` groovy
androidTestcompile 'br.com.concretesolutions:kappuccino:0.9.9'
```

And you're ready to go!

### Obs: In case you have any module conflicts, try to exclude the conflict module, for example:

``` groovy
androidTestCompile('br.com.concretesolutions:kappuccino:0.9.9', {
        exclude group: 'com.android.support'
    })
```

## Other examples

Soon I'll have a more complete sample and examples. For now, you can check some dummy testes in sample code.

## LICENSE

This project is available under Apache Public License version 2.0. See [LICENSE](LICENSE).




