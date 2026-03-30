@ECHO OFF
SETLOCAL
SET WRAPPER_DIR=%~dp0\.mvn\wrapper
SET WRAPPER_JAR=%WRAPPER_DIR%\maven-wrapper.jar

IF NOT EXIST "%WRAPPER_JAR%" (
  FOR /F "usebackq tokens=1,2 delims==" %%A IN ("%WRAPPER_DIR%\maven-wrapper.properties") DO (
    IF "%%A"=="wrapperUrl" SET WRAPPER_URL=%%B
  )
  IF "%WRAPPER_URL%"=="" (
    ECHO Maven Wrapper URL not specified.
    EXIT /B 1
  )
  IF NOT EXIST "%WRAPPER_DIR%" MKDIR "%WRAPPER_DIR%"
  ECHO Downloading Maven Wrapper from %WRAPPER_URL%
  PowerShell -Command "(New-Object System.Net.WebClient).DownloadFile('%WRAPPER_URL%', '%WRAPPER_JAR%')"
)

java -jar "%WRAPPER_JAR%" %*
ENDLOCAL
