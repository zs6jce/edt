@rem ***************************************************************************
@rem Copyright (c) 2012 IBM Corporation and others.
@rem All rights reserved. This program and the accompanying materials
@rem are made available under the terms of the Eclipse Public License v1.0
@rem which accompanies this distribution, and is available at
@rem http://www.eclipse.org/legal/epl-v10.html
@rem
@rem Contributors:
@rem     IBM Corporation - initial API and implementation
@rem ***************************************************************************
set path="C:\IBM\SDP\8013\jdk\bin";
java -Xmx128m -jar C:\Work\JFlex\lib\jflex.jar error.flex
del ErrorLexer.java~