@REM
@REM Timer Review  -  a personal time management tool
@REM
@REM
@REM Copyright (C)  2012 - 2014 Parentini Massimiliano
@REM Project home page: http://www.timer-review.net
@REM
@REM
@REM This program is free software: you can redistribute it and/or modify
@REM it under the terms of the GNU General Public License as
@REM published by the Free Software Foundation, either version 3 of the
@REM License, or (at your option) any later version.
@REM
@REM This program is distributed in the hope that it will be useful,
@REM but WITHOUT ANY WARRANTY; without even the implied warranty of
@REM MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
@REM GNU General Public License for more details.
@REM
@REM You should have received a copy of the GNU General Public
@REM License along with this program.  If not, see
@REM <http://www.gnu.org/licenses/gpl-3.0.html>.
@REM

set TIMER_HOME=D:\Users\eul0963\Documents\sts_wks\timer

rmdir /S /Q %TIMER_HOME%\target
rmdir /S /Q D:\servers\Apache24\htdocs\staging
call mvn site site:stage
ROBOCOPY /E /COPYALL %TIMER_HOME%\target\staging  D:\servers\Apache24\htdocs\staging
