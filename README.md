<a href="https://codebeat.co/projects/github-com-heitorcolangelo-kappuccino-develop"><img alt="codebeat badge" src="https://codebeat.co/badges/56bd966e-a50b-4f9c-a3d5-f7fdb9f0afca" /></a>
<a href="https://www.bitrise.io/app/e3a8637b620dcfff/status.svg?token=Z0f8z81pqA5QUo7QEtGvzw"><img alt="bitrise badge" src="https://www.bitrise.io/app/e3a8637b620dcfff/status.svg?token=Z0f8z81pqA5QUo7QEtGvzw" /></a>
[ ![Download](https://api.bintray.com/packages/concrete/concrete-maven/kappuccino/images/download.svg) ](https://bintray.com/concrete/concrete-maven/kappuccino/_latestVersion)
# kappuccino
A framework to simplify the way you do instrumentation tests in your app, using <a href="https://google.github.io/android-testing-support-library/docs/espresso/">Espresso</a> and <a href="https://kotlinlang.org/">Kotlin</a>.

Here is how you do instrumentation tests today, using simply Espresso:

``` kotlin
@Test fun loginFieldsAreVisible() {
  onView(withId(R.id.username)).check(matches(isDisplayed())
  onView(withId(R.id.password)).check(matches(isDisplayed())
  onView(withId(R.id.login_button)).check(matches(isDisplayed())
}
```
This is just to check a simple login screen, and we are not even considering that we may need to scroll to one of these views,
due to small screens support.

With scroll, our test will be something like this:

``` kotlin
@Test fun loginFieldsAreVisible() {
  onView(withId(R.id.username)).perform(scrollTo()).check(matches(isDisplayed())
  onView(withId(R.id.password)).perform(scrollTo()).check(matches(isDisplayed())
  onView(withId(R.id.login_button)).perform(scrollTo()).check(matches(isDisplayed())
}
```

We have to repeat a lot of code, this makes the tests hard to read and understand at a first look. Also you may forget some scrollTo(), or mismatch the check function.
At the end, all we want to do is check if the views with these ids are displayed.

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

1 - Setup kotlin in your project, see the instructions <a href="https://kotlinlang.org/docs/tutorials/kotlin-android.html">here</a>

2 - Create a kotlin directory into 'src/androidTest/', check the sample code for reference.

3 - Set you sourceDataSet into your build.gradle file

``` groovy
sourceSets {
    androidTest.java.srcDirs = ['src/androidTest/kotlin']
  }
```

4 - Add library and dependencies into your build.gradle file and sync

``` groovy
androidTestImplementation 'br.com.concretesolutions:kappuccino:$latest.version'
```

5 - This library depends on the following libraries:
- [Espresso](https://developer.android.com/training/testing/espresso/index.html): mandatory
- [UiAutomator](https://developer.android.com/training/testing/ui-automator.html): optional, used for runtime permissions

So, ensure those libraries are also in your dependencies.

``` groovy
androidTestImplementation "com.android.support.test.espresso:espresso-intents:$versions.espresso"
androidTestImplementation "com.android.support.test.espresso:espresso-core:$versions.espresso"
androidTestImplementation "com.android.support.test.espresso:espresso-contrib:$versions.espresso"
androidTestImplementation "com.android.support.test.uiautomator:uiautomator-v18:$versions.uiAutomator"
```

And you're ready to go!

#### If you have any module conflicts, try to exclude the conflicting module, for example:

``` groovy
androidTestImplementation('br.com.concretesolutions:kappuccino:$latest.version', {
        exclude group: 'com.android.support'
    })
```

### Assertion methods
These are the methods to make view assertions
``` kotlin
checked {}
notChecked {}

clickable {}
notClickable {}

selected {}
notSelected {}

displayed {}
notDisplayed {}

notExist {}
```

### Action methods
These are methods to interact with views
``` kotlin
click {}
doubleClick {}
longClick {}

typeText {}
clearText {}
```

### Scroll
The scroll method is now a parameter for all the above methods, the default value is false, for example:

``` kotlin
@Test fun scrollToButton_andClick() {
  click(scroll = true) {
    id(R.id.login_button)
  }
}
```
In this case, it will scroll to the view and click. If you don't provide a parameter, the scroll will not happen.

### Combine matchers (Matchers.allOf)
To combine multiple matchers, use the allOf method:

``` kotlin
@Test fun scrollToButton_andClick() {
  click(scroll = true) {
    allOf {
        id(R.id.login_button)
        text(R.string.login_button_text)
    }
  }
}
```

### Hierarchy
There are two methods of hierarchy matchers: Parent and Descendant.

#### Parent
You can use Parent method with two different approaches: block matching or combining.

<b>1 - Block matching:</b><br />
For block matching, pass the parentId as method parameter.

Then, kappuccino will match all the views inside the block:

``` kotlin
@Test fun matchParent_blockMatching_example() {
  displayed {
    parent(R.id.parent) {
        id(R.id.username)
        id(R.id.password)
        id(R.id.login_button)
    }
  }
}
```
Here, kappuccino will check if all the views (username, password and login_button) are descendant of the declared parent, and are displayed.

For better understanding, the code above is equivalent to the one below, using pure Espresso:

``` kotlin
@Test fun matchParent_example() {
    onView(
        allOf(isDescendantOf(withId(R.id.parent)), withId(R.id.username)))
        .check(matches(isDisplayed()))
    onView(
        allOf(isDescendantOf(withId(R.id.parent)), withId(R.id.password)))
        .check(matches(isDisplayed()))
    onView(
        allOf(isDescendantOf(withId(R.id.parent)), withId(R.id.login_button)))
        .check(matches(isDisplayed()))
}
```

<b>2 - Combination of matchers:</b><br />
You can use the parent method as a combination of matchers:

``` kotlin
@Test fun matchParent_combining_example() {
    displayed {
        allOf {
            parent {
                id(R.id.parent)
            }
            id(R.id.username)
        }
    }
}
```
Here, you will check if the view with id = R.id.username, and with parent with id = R.id.parent, is displayed

#### Descendant
It works just like the parent method, for both cases (block matching and combining matchers)

``` kotlin
@Test fun descendant_block_example() {
    displayed {
        allOf {
            descendant {
                id(R.id.username)
            }
            id(R.id.parent)
        }
    }
}
```
Here, we'll check if the parent, with child R.id.username is displayed.
Same use for block matching.

### RecyclerView
To interact with the recycler view:

``` kotlin
@Test fun recyclerView_example() {
    recyclerView(R.id.recycler_view) {
        sizeIs(10)
        atPosition(3) {
            displayed {
                id(R.id.item_description)
                text(R.string.description_text)
                text("Item header text")
            }
        }
    }
}
```

To type text in a RecyclerView item's EditText:

``` kotlin
@Test fun recyclerView_textInput_example() {
    recyclerView(R.id.recycler_view) {
        atPosition(0) {
            typeText(R.id.editText, "Position 0")
        }

        atPosition(1) {
            typeText(R.id.editText, "Position 1")
        }
    }
}
```

To swipe a RecyclerView's item left or right:

``` kotlin
@Test fun recyclerView_swipeLeft_example() {
    recyclerView(R.id.recycler_view() {
        atPosition(0) {
            swipeLeft()
        }

        atPosition(1) {
            swipeRight()
        }
    }
}
```

### Menu and action bar
To interact with the options menu:

``` kotlin
@Test
fun whenClickingOnItem1_shouldShowCorrectText() {
    menu {
        onItem(R.string.item_1) {
            click()
        }
    }

    displayed {
        text(R.string.item_1_selected)
    }
}
```

To interact with the action bar:

``` kotlin
@Test
fun whenClickingOnActionBarItem_shouldClearText() {
    menu(openOptionsMenu = false) {
        onActionBarItem(R.id.item_clear) {
            click()
        }
    }

    notDisplayed {
        id(R.id.txt_menu)
    }
```

### Matchers
You can use the following matchers:
``` kotlin
fun id(@IdRes viewId: Int)
fun text(@StringRes textId: Int)
fun text(text: String)
fun contentDescription(@StringRes contentDescriptionId: Int)
fun contentDescription(contentDescription: String)
fun image(@DrawableRes imageId: Int)
fun textColor(@ColorRes colorId: Int)
fun parent(@IdRes parentId: Int)
fun descendant(@IdRes descendantId: Int)
fun custom(viewMatcher: Matcher<View>) // Here you can pass a custom matcher
```

### Intent Matchers
You can match intents easily now:
``` kotlin
@Test
fun intentMatcherTest() {
    val WHATS_PACKAGE_NAME = "com.whatsapp"
    val PLAY_STORE_URL = "https://play.google.com/store/apps/details?id="
    Intents.init()
    matchIntent {
        action(Intent.ACTION_VIEW)
        url(PLAY_STORE_URL + WHATS_PACKAGE_NAME)
        result {
           ok()
        }
    }

    click {
        id(R.id.btn_start_activity)
    }

    matchIntent {
        action(Intent.ACTION_VIEW)
        url(PLAY_STORE_URL + WHATS_PACKAGE_NAME)
    }

    Intents.release()
}
```
If you use some of the result methods (resultOk, resultCanceled, resultData) it's going to be like use the Espresso <i>intending</i> method.
If you DON'T use any of the result methods, it's the same as use the Espresso <i>intended</i> method.
The above code it will be something like this, without kappuccino
``` kotlin
@Test
fun intentMatcherTest() {
    val WHATS_PACKAGE_NAME = "com.whatsapp"
    val PLAY_STORE_URL = "https://play.google.com/store/apps/details?id="
    Intents.init()

    val matcher = allOf(hasAction(Intent.ACTION_VIEW), hasData(Uri.parse(PLAY_STORE_URL + WHATS_PACKAGE_NAME)))
    val result = ActivityResult(Activity.RESULT_OK, null)
    intending(matcher).respondWith(result);

    click {
        id(R.id.btn_start_activity)
    }

    intended(matcher)

    Intents.release()
}
```
You can also use a custom intent matcher with the <i>custom</i> method

### Runtime permissions
Easily handle runtime permissions
``` kotlin
@Test
fun grantContactsPermission() {
    click {
        id(R.id.btn_request_permission)
    }

    runtimePermission(Manifest.permission.READ_CONTACTS) {
        allow()
    }

    displayed {
        text("PERMISSION GRANTED")
    }
}
```

### Background matcher
Check view's background. The background must be VectorDrawable, BitmapDrawable or ColorDrawable
``` kotlin
@Test
fun backgroundColorTest() {
    displayed {
        allOf {
            id(R.id.view_background)
            background(R.drawable.ic_android)
        }
    }

    displayed {
        background(R.color.colorAccent)
    }
}
```

#### For more examples, please check the sample code.

#### Wiki: coming soon.

<b>Tip:</b> this framework was based on <a href="https://news.realm.io/news/kau-jake-wharton-testing-robots/">Robots Pattern</a>. It's a good idea to use this framework in combination with this pattern.
## LICENSE

This project is available under Apache Public License version 2.0. See [LICENSE](LICENSE).




