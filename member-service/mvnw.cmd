@REM Maven wrapper batch script for Windows
@REM
@REM To use the Maven wrapper, specify the Maven goal or phase:
@REM     mvnw clean install
@REM     mvnw spring-boot:run

@REM The batch script is version-insensitive of Windows' version

@echo off

setlocal

set APP_HOME=%~dp0

if not exist "%APP_HOME%\mvn\bin\mvn.cmd" (
    echo Error: Maven wrapper not configured properly
    echo Please ensure Maven is properly set up
    exit /b 1
)

"%APP_HOME%\mvn\bin\mvn.cmd" %*
endlocal
