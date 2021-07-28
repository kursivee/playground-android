# Testing Room Database
Goal of this application is to test Room database interactions purely with Espresso

[Database Tests](https://github.com/kursivee/playground-android/tree/master/RoomTesting/app/src/androidTest/java/com/kursivee/roomtesting)

## Testing Simple Database Actions
`UserDaoTest` tests for the `User` and `Address` entities.


- Tests create, find, get list
- Tests the foreign key constraint by deleting the user and cascading the address deletion
- [Android Guide: Test and debug your database](https://developer.android.com/training/data-storage/room/testing-db)

## Testing Migrations
`MigrationTest` tests migrating between different version of Room. I opted for AutoMigration since I didn't want to go to indepth with Migrations

- Tests migration of table name `User` to `NewUserTable`
- [Testing Room Migrations by Florina Muntenescu](https://medium.com/androiddevelopers/testing-room-migrations-be93cdb0d975)
- [Android Guide: Testing Room Migrations](https://developer.android.com/training/data-storage/room/migrating-db-versions#groovy)