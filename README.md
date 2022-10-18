POCA Readme

Welcome to POCA!

# POCA

POCA stands for Personal Online Collections Application.
All have all collected something in our lives. Having it all in one place is the most convient. 
POCA is your personal organizer, that stores any and all collections that you want. POCA is avaliable at the touch of a button.
Always ready for you and willing to satify your need to keep organizational structures.


## Default login

Username: test@test.com
Password: 123456


## Authors

- [Thomas Rossouw](ST10121910@vcconnect.edu.za)


## Features
*As of V1
- Login and Register.
- Crate Categories.
- Set Goal for Collections.
- Add items to Collections.
- View items in a list. 
- Take a picture and store it.
*As of V2
- visual progress bar 
- graph desiplaying target goals 


## Deployment

Deployment of this application is split:

* The authentication is down through Firebase, this means that the Login and Register is connected to an online database.

* The data is stored within SQLite. It is a local database within Android Studio.


## FAQ

#### Why can't I log in even though I am a registered user?

Are you connected to a WiFi connection, as the authenticatiom is an online feature.

#### Why won't my Listview display my data?

You may need to refresh the page.

#### Why do I have to sign in everytime?

We are looking at adding a logout feature that will revert back to the login page. 
We are also looking at adding a feature to allow you to stay signed-in.


## Troubleshooting
- Restart your Internet router.
- Put your device in Flight Mode to reset the adapters.
- Force stop the app.
- Uninstall and reinstall the app.
- Clear the app's cache and data.
- Check for any new Android updats.


## Acknowledgements

 - [User Authentication and CRUD Operation with Firebase Realtime Database in Android](https://www.geeksforgeeks.org/user-authentication-and-crud-operation-with-firebase-realtime-database-in-android/)
 - [Debug your database with the Database Inspector](https://developer.android.com/studio/inspect/database)
 - [Android Button Design](https://androiddvlpr.com/android-button-design/)
 - [vistacreate](https://create.vista.com/home/)
 - [Bottom navigation](https://material.io/components/bottom-navigation/android#using-bottom-navigation)
 - [Lists](https://material.io/components/lists)
 - [Introducing Material Theme Builder](https://material.io/blog/material-theme-builder)
 - [Google Fonts](https://fonts.google.com/icons?selected=Material+Icons&icon.query=add)
 - [Test fields](https://material.io/components/text-fields)
 - [Android Studio does not show layout preview](https://stackoverflow.com/questions/34499839/android-studio-does-not-show-layout-preview)
 - [How to validate registration data and insert it into SQLite Database?](https://stackoverflow.com/questions/30800366/how-to-validate-registration-data-and-insert-it-into-sqlite-database)
 - [Android Button Design, Custom Button, Round Button, Color](https://www.journaldev.com/19850/android-button-design-custom-round-color)
 - [Firebase](https://firebase.google.com/)
 - [VC Learn](https://myvc.iielearn.ac.za/ultra/)
 - [Android Custom ListView (Adding Images, sub-title)](https://www.javatpoint.com/android-custom-listview)
 - [DeepCrazyWorld](https://www.deepcrazyworld.com/how-to-make-barchart-graph-in-android-studio/)
 - [Envatotuts+](https://code.tutsplus.com/tutorials/how-to-generate-apk-and-signed-apk-files-in-android-studio--cms-37927)
 - [Microsoft](https://docs.microsoft.com/en-us/dotnet/api/android.content.intent.putextra?view=xamarin-android-sdk-12)
 - [maketecheasier](https://www.maketecheasier.com/fix-android-apps-not-working/)


## Support

For support, email ST10121910@vcconnect.edu.za.


## License

Copyright (c) [2022] [Thomas Arnaud Rossouw]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.


## Lessons Learned

Working with an online database such as Firebase can be unpredicable. 
By having time you can achieve the impossible. Being fortunate to be able to have worked on a backup that 
uses the SQLite Database, and is my 'get-out-of-jail' card.  
Documentation is just as important as the code. Researching helps to create an understanding of an idea, allowing for development.
