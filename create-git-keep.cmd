@echo off
for /f "delims=" %%d in ('dir /ad /b /s') do (
    dir /a /b "%%d" | findstr . >nul
    if errorlevel 1 (
        echo Creating .gitkeep in %%d
        echo. > "%%d\.gitkeep"
    )
)
