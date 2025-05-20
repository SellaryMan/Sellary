import type { Metadata } from "next";
import { Geist, Geist_Mono } from "next/font/google";
import Main from "@/components/layout/Main";
import "../styles/globals.css";

const geistSans = Geist({
  variable: "--font-geist-sans",
  subsets: ["latin"],
});

const geistMono = Geist_Mono({
  variable: "--font-geist-mono",
  subsets: ["latin"],
});

export const metadata: Metadata = {
  title: "Sellary",
  description: "Sellary"
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body
        className={`${geistSans.variable} ${geistMono.variable} antialiased`}
      >
        <div className="flex flex-col min-h-screen min-w-[1600px]">
          {/* <Header/> */}
          <div className="flex flex-1 w-full">
            <Sidebar/>
            <div className="flex-1 overflow-x-auto">
              <Main>{children}</Main>
            </div>
          </div>
        </div>
       

      </body>
    </html>
  );
}
