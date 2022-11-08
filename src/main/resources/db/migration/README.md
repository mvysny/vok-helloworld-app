# Database Migration Files

Lists SQL files which will allow you to keep your database structure up-to-date.
Uses the [Flyway](https://flywaydb.org/) framework.

Database is upgraded on every server boot (from the `Bootstrap` class), to ensure it's always up-to-date.
See the [Migration Naming Guide](https://flywaydb.org/documentation/migrations#naming) for more details.
