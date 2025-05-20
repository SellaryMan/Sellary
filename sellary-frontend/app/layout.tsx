import type { Metadata } from "next";
import { Geist, Geist_Mono } from "next/font/google";
import Main from "@/components/layout/Main";
import { SidebarProvider,SidebarTrigger } from "@/components/ui/sidebar";
import AppSidebar from "@/components/layout/app-sidebar";
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
            <div className="flex-1 overflow-x-auto">
              <SidebarProvider>
                <AppSidebar/>
                  <SidebarTrigger/>
                  <Main>{children}</Main>
              </SidebarProvider>
            </div>
          </div>
        </div>
      </body>
    </html>
  );
}
